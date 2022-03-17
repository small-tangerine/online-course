package com.course.admin.controller;

import com.course.api.dto.FileChunkDTO;
import com.course.api.dto.FileChunkResultDTO;
import com.course.api.vo.admin.CourseVideoVo;
import com.course.commons.model.Response;
import com.course.commons.utils.VideoUtil;
import com.course.component.component.UploadComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author zhangyukang
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
     * @return
     */
    @GetMapping("chunk")
    public Response checkChunkExist(FileChunkDTO chunkDTO) {
        FileChunkResultDTO fileChunkCheckDTO= uploadComponent.checkChunkExist(chunkDTO);
            return Response.ok(fileChunkCheckDTO);
    }


    /**
     * 上传文件分片
     *
     * @param chunkDTO
     * @return
     */
    @PostMapping("chunk")
    public Response uploadChunk(FileChunkDTO chunkDTO) throws IOException {
            uploadComponent.uploadChunk(chunkDTO);
            return Response.ok(chunkDTO.getIdentifier());
    }

    /**
     * 请求合并文件分片
     *
     * @param chunkDTO
     * @return
     */
    @PostMapping("merge")
    public Response mergeChunks(@RequestBody FileChunkDTO chunkDTO) throws IOException {
        CourseVideoVo vo=uploadComponent.mergeChunk(chunkDTO.getIdentifier(), chunkDTO.getFilename(), chunkDTO.getTotalChunks());
        long duration = VideoUtil.getDuration(vo.getFileUrl());
        vo.setVideoLength(duration);
        return Response.ok(vo);
    }

}
