package utils;

public class PageSupport {
    // 用戶輸入當前頁面
    private int currentPage = 1;
    // 總比數
    private int totalCount = 0;
    // 每頁比數
    private int pageSize = 0;
    // 總頁數 totalCount/pageSize +1
    private int totalPageCount = 1;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if(currentPage > 0) this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount > 0){
            this.totalCount = totalCount;
            // 設置總頁數
            this.setTotalPageCountByRs();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize > 0) this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

//    public void setTotalPageCount(int totalPageCount) {
//        this.totalPageCount = totalPageCount;
//    }
    public void setTotalPageCountByRs(){
        if(this.totalCount % this.pageSize == 0){
            this.totalPageCount = this.totalCount/this.pageSize;
        }else if(this.totalCount % this.pageSize > 0){
            this.totalPageCount = this.totalCount/this.pageSize +1;
        }else{
            this.totalPageCount = 0;
        }
    }
}
