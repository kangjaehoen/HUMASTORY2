package com.kosta.humanstory.domain;

import lombok.Data;

@Data
public class AttachVO {
    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean fileType;
    private int lpNum;
}
