package com.example.mt.card;

import java.time.LocalDate;

public class GeneralStatusCountDateDTO {
    private LocalDate date;
    private Long visitedCount;
    private Long downloadedCount;

    public GeneralStatusCountDateDTO(LocalDate date, Long visitedCount, Long downloadedCount) {
        this.date = date;
        this.visitedCount = visitedCount;
        this.downloadedCount = downloadedCount;
    }

    public GeneralStatusCountDateDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
