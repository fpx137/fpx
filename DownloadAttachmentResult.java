package com.ruoyi.system.api.domain.SysFlow.flowexample.Return;

import java.io.Serializable;

public class DownloadAttachmentResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private result result;
    private Boolean   success;

    public DownloadAttachmentResult.result getResult() {
        return result;
    }

    public void setResult(DownloadAttachmentResult.result result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static class result{
        private String fileId;
        private Long spaceId;
        private String  downloadUri;

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public Long getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(Long spaceId) {
            this.spaceId = spaceId;
        }

        public String getDownloadUri() {
            return downloadUri;
        }

        public void setDownloadUri(String downloadUri) {
            this.downloadUri = downloadUri;
        }
    }
}
