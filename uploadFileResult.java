package com.ruoyi.system.api.domain.SysFlow.flowexample.Return;

public class uploadFileResult {
    private Long fileId;
    private String fileName;
    private Long spaceId;
    private Long fileSize;
    private String fileType;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "uploadFileResult{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", spaceId=" + spaceId +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
