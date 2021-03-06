package com.course.admin.controller;

import com.course.api.dto.FileChunkDTO;
import com.course.api.dto.FileChunkResultDTO;
import com.course.api.vo.admin.CourseVideoVo;
import com.course.commons.model.Response;
import com.course.component.component.UploadComponent;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @Date 2021/1/16 19:22
 * @Version 1.0
 * @Modify XJLZ
 **/
@RequestMapping("/upload")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class UploadController {

    private final UploadComponent uploadComponent;

    /**
     * 检查分片是否存在
     *
     * @return response
     */
    @GetMapping("chunk")
    public Response checkChunkExist(FileChunkDTO chunkDTO) {
        FileChunkResultDTO fileChunkCheckDTO = uploadComponent.checkChunkExist(chunkDTO);
        return Response.ok(fileChunkCheckDTO);
    }


    /**
     * 上传文件分片
     *
     * @param chunkDTO 分片实体
     * @return response
     */
    @PostMapping("chunk")
    public Response uploadChunk(FileChunkDTO chunkDTO) throws IOException {
        uploadComponent.uploadChunk(chunkDTO);
        return Response.ok(chunkDTO.getIdentifier());
    }

    /**
     * 请求合并文件分片
     *
     * @param chunkDTO 分片实体
     * @return response
     */
    @PostMapping("merge")
    public Response mergeChunks(@RequestBody FileChunkDTO chunkDTO) throws IOException, EncoderException {
        CourseVideoVo vo = uploadComponent.mergeChunk(chunkDTO.getIdentifier(), chunkDTO.getFilename(), chunkDTO.getTotalChunks());
        // 计算视频时长
        Encoder encoder = new Encoder();
        MultimediaInfo m = encoder.getInfo(new File(vo.getFileUrl()));
        long duration = m.getDuration() / 1000;
        vo.setVideoLength(duration);
        return Response.ok(vo);
    }

}
