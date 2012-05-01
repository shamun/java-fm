/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package figoo.classes;

import com.google.gdata.data.photos.impl.ExifTag;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lada Riha
 */
public class PhotoInfo {

    private String desc;
    private String title;
    private String tags;
    private Long height;
    private Long width;
    private Long size;
    private Date date;
    private String id;
    private Collection<ExifTag> exifs;

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the height
     */
    public Long getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Long height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public Long getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Long width) {
        this.width = width;
    }

    /**
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the exifs
     */
    public Collection<ExifTag> getExifs() {
        return exifs;
    }

    /**
     * @param exifs the exifs to set
     */
    public void setExifs(Collection<ExifTag> exifs) {
        this.exifs = exifs;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id= id;
    }
    /**
     *
     * @return
     */
    public String getId() {
        return this.id;
    }


}
