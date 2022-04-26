package com.course.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @Date 2021/1/17 10:14
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class FileChunkResultDTO {
    /**
     * 是否跳过上传
     */
    private Boolean skipUpload;

    /**
     * 已上传分片的集合
     */
    private Set<Integer> uploaded;

    public Boolean getSkipUpload() {
        return skipUpload;
    }

    public void setSkipUpload(Boolean skipUpload) {
        this.skipUpload = skipUpload;
    }

    public Set<Integer> getUploaded() {
        return uploaded;
    }

    public void setUploaded(Set<Integer> uploaded) {
        this.uploaded = uploaded;
    }


    public FileChunkResultDTO(Boolean skipUpload, Set<Integer> uploaded) {
        this.skipUpload = skipUpload;
        this.uploaded = uploaded;
    }

    public FileChunkResultDTO(Boolean skipUpload) {
        this.skipUpload = skipUpload;
    }
}
