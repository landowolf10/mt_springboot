package com.example.mt.card;

public class GeneralStatusCountDTO {
    private Long visitedCount;
    private Long downloadedCount;

    public GeneralStatusCountDTO(Long visitedCount, Long downloadedCount) {
        this.visitedCount = visitedCount;
        this.downloadedCount = downloadedCount;
    }

    public GeneralStatusCountDTO() {
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
}
