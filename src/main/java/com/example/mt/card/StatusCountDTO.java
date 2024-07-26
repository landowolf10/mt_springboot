package com.example.mt.card;

public class StatusCountDTO {
    Integer clientId;
    private Long visitedCount;
    private Long downloadedCount;

    public StatusCountDTO(Integer clientId, Long visitedCount, Long downloadedCount) {
        this.clientId = clientId;
        this.visitedCount = visitedCount;
        this.downloadedCount = downloadedCount;
    }

    public StatusCountDTO() {
    }

    public Long getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(Long visitedCount) {
        this.visitedCount = visitedCount;
    }

    public Long getDownloadedCount() {
        return downloadedCount;
    }

    public void setDownloadedCount(Long downloadedCount) {
        this.downloadedCount = downloadedCount;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
