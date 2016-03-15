
package com.ss.criteria;

/**
 * [分页类]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月18日
 * @see
 * @since zhubu-V1001
 */
public class CommonPage
{
    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer number;

    public Integer getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage)
    {
        this.currentPage = currentPage == null ? 0 : currentPage;
        if (this.currentPage > 0)
        {
            this.currentPage = this.currentPage - 1;
        }
        if (this.number != null)
        {
            this.currentPage = this.currentPage * this.number;
        }
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number == null ? 0 : number;
        if (this.currentPage != null)
        {
            this.currentPage = this.currentPage * this.number;
        }
    }

}
