package com.course.component.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.course.api.dto.FileChunkDTO;
import com.course.api.dto.FileChunkResultDTO;
import com.course.api.vo.admin.CourseVideoVo;
import com.course.commons.constant.CommonConstant;
import com.course.commons.utils.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 上传组件
 *
 * @since 2022-03-16
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class UploadComponent {

    private static final String uploadFolder = "C:\\nginx-1.16.1\\upload\\video\\";

    /**
     * 检查文件是否存在，如果存在则跳过该文件的上传，如果不存在，返回需要上传的分片集合
     *
     * @param chunkDTO
     * @return
     */
    public FileChunkResultDTO checkChunkExist(FileChunkDTO chunkDTO) {
        //1.检查文件是否已上传过
        //1.1)检查在磁盘中是否存在
        String fileFolderPath = getFileFolderPath(chunkDTO.getIdentifier());
        log.info("fileFolderPath-->{}", fileFolderPath);
        String filePath = getFilePath(chunkDTO.getIdentifier(), chunkDTO.getFilename());
        File file = new File(filePath);
        boolean exists = file.exists();
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            boolean mkdirs = fileFolder.mkdirs();
            log.info("准备工作,创建文件夹,fileFolderPath:{},mkdirs:{}", fileFolderPath, mkdirs);
        }
        // 断点续传，返回已上传的分片
        return new FileChunkResultDTO(false, Collections.emptySet());
    }


    /**
     * 上传分片
     *
     * @param chunkDTO
     */
    public void uploadChunk(FileChunkDTO chunkDTO) throws IOException {
        //分块的目录
        String chunkFileFolderPath = getChunkFileFolderPath(chunkDTO.getIdentifier());
        log.info("分块的目录 -> {}", chunkFileFolderPath);
        File chunkFileFolder = new File(chunkFileFolderPath);
        if (!chunkFileFolder.exists()) {
            boolean mkdirs = chunkFileFolder.mkdirs();
            log.info("创建分片文件夹:{}", mkdirs);
        }
        //写入分片
        InputStream inputStream = chunkDTO.getFile().getInputStream();
        FileOutputStream outputStream = new FileOutputStream(chunkFileFolderPath + chunkDTO.getChunkNumber());
        IoUtil.copy(inputStream, outputStream);
        log.info("文件标识:{},chunkNumber:{}", chunkDTO.getIdentifier(), chunkDTO.getChunkNumber());
        outputStream.close();
        inputStream.close();
    }


    public CourseVideoVo mergeChunk(String identifier, String fileName, Integer totalChunks) throws IOException {
        return mergeChunks(identifier, fileName, totalChunks);
    }

    /**
     * 合并分片
     *
     * @param identifier
     * @param filename
     */
    private CourseVideoVo mergeChunks(String identifier, String filename, Integer totalChunks) throws IOException {
        String chunkFileFolderPath = getChunkFileFolderPath(identifier);
        String filePath = getFilePath(identifier, filename);
        // 检查分片是否都存在
        CourseVideoVo courseVideoVo=null;
        if (checkChunks(chunkFileFolderPath, totalChunks)) {
            File chunkFileFolder = new File(chunkFileFolderPath);
            File mergeFile = new File(filePath);
            File[] chunks = chunkFileFolder.listFiles();
            //排序
            List fileList = Arrays.asList(chunks);
            Collections.sort(fileList, (Comparator<File>) (o1, o2) -> {
                return Integer.parseInt(o1.getName()) - (Integer.parseInt(o2.getName()));
            });
            RandomAccessFile randomAccessFileWriter = new RandomAccessFile(mergeFile, "rw");
            byte[] bytes = new byte[1024];
            for (File chunk : chunks) {
                RandomAccessFile randomAccessFileReader = new RandomAccessFile(chunk, "r");
                int len;
                while ((len = randomAccessFileReader.read(bytes)) != -1) {
                    randomAccessFileWriter.write(bytes, 0, len);
                }
                randomAccessFileReader.close();
            }
            randomAccessFileWriter.close();
             courseVideoVo = new CourseVideoVo().setFileSize(mergeFile.length())
                    .setUrl("http://localhost/video/" + getSuffixFilePath(identifier, filename))
                    .setFileUrl(filePath);
        }else {
            FileUtil.del(CommonConstant.Video_PREFIX + identifier);
            Assert.isTrue(false,"文件分片上传不完整请重新上传");
        }
        FileUtil.del(CommonConstant.Video_PREFIX + identifier);
        return courseVideoVo;
    }

    /**
     * 检查分片是否都存在
     *
     * @param chunkFileFolderPath
     * @param totalChunks
     * @return
     */
    private boolean checkChunks(String chunkFileFolderPath, Integer totalChunks) {
        try {
            for (int i = 1; i <= totalChunks + 1; i++) {
                File file = new File(chunkFileFolderPath + File.separator + i);
                if (file.exists()) {
                    continue;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 得到文件的绝对路径
     *
     * @param identifier
     * @param filename
     * @return
     */
    private String getFilePath(String identifier, String filename) {
        String ext = filename.substring(filename.lastIndexOf("."));
        return CommonConstant.Video_PREFIX + identifier+ext;
    }

    /**
     * 得到文件的绝对路径
     *
     * @param identifier
     * @param filename
     * @return
     */
    private String getSuffixFilePath(String identifier, String filename) {
        String ext = filename.substring(filename.lastIndexOf("."));
        return identifier + ext;
    }

    /**
     * 得到文件的相对路径
     *
     * @param identifier
     * @param filename
     * @return
     */
    private String getFileRelativelyPath(String identifier, String filename) {
        String ext = filename.substring(filename.lastIndexOf("."));
        return "/" + identifier.charAt(0) + "/" +
                identifier.charAt(1) + "/" +
                identifier + "/" + identifier
                + ext;
    }


    /**
     * 得到分块文件所属的目录
     *
     * @param identifier
     * @return
     */
    private String getChunkFileFolderPath(String identifier) {
        return getFileFolderPath(identifier) + "chunks" + File.separator;
    }

    /**
     * 得到文件所属的目录
     *
     * @param identifier
     * @return
     */
    private String getFileFolderPath(String identifier) {
        return CommonConstant.Video_PREFIX + identifier + File.separator;
    }
}
