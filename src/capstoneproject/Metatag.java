package capstoneproject;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Metatag {

    @Expose
    private String viewport;
    @Expose
    private String author;
    @SerializedName("og:url")
    @Expose
    private String ogUrl;
    @SerializedName("og:type")
    @Expose
    private String ogType;
    @SerializedName("og:title")
    @Expose
    private String ogTitle;
    @SerializedName("og:image")
    @Expose
    private String ogImage;
    @SerializedName("og:site_name")
    @Expose
    private String ogSiteName;
    @SerializedName("og:description")
    @Expose
    private String ogDescription;
    @SerializedName("fb:admins")
    @Expose
    private String fbAdmins;
    @SerializedName("fb:app_id")
    @Expose
    private String fbAppId;

    /**
     * 
     * @return
     *     The viewport
     */
    public String getViewport() {
        return viewport;
    }

    /**
     * 
     * @param viewport
     *     The viewport
     */
    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

    /**
     * 
     * @return
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The ogUrl
     */
    public String getOgUrl() {
        return ogUrl;
    }

    /**
     * 
     * @param ogUrl
     *     The og:url
     */
    public void setOgUrl(String ogUrl) {
        this.ogUrl = ogUrl;
    }

    /**
     * 
     * @return
     *     The ogType
     */
    public String getOgType() {
        return ogType;
    }

    /**
     * 
     * @param ogType
     *     The og:type
     */
    public void setOgType(String ogType) {
        this.ogType = ogType;
    }

    /**
     * 
     * @return
     *     The ogTitle
     */
    public String getOgTitle() {
        return ogTitle;
    }

    /**
     * 
     * @param ogTitle
     *     The og:title
     */
    public void setOgTitle(String ogTitle) {
        this.ogTitle = ogTitle;
    }

    /**
     * 
     * @return
     *     The ogImage
     */
    public String getOgImage() {
        return ogImage;
    }

    /**
     * 
     * @param ogImage
     *     The og:image
     */
    public void setOgImage(String ogImage) {
        this.ogImage = ogImage;
    }

    /**
     * 
     * @return
     *     The ogSiteName
     */
    public String getOgSiteName() {
        return ogSiteName;
    }

    /**
     * 
     * @param ogSiteName
     *     The og:site_name
     */
    public void setOgSiteName(String ogSiteName) {
        this.ogSiteName = ogSiteName;
    }

    /**
     * 
     * @return
     *     The ogDescription
     */
    public String getOgDescription() {
        return ogDescription;
    }

    /**
     * 
     * @param ogDescription
     *     The og:description
     */
    public void setOgDescription(String ogDescription) {
        this.ogDescription = ogDescription;
    }

    /**
     * 
     * @return
     *     The fbAdmins
     */
    public String getFbAdmins() {
        return fbAdmins;
    }

    /**
     * 
     * @param fbAdmins
     *     The fb:admins
     */
    public void setFbAdmins(String fbAdmins) {
        this.fbAdmins = fbAdmins;
    }

    /**
     * 
     * @return
     *     The fbAppId
     */
    public String getFbAppId() {
        return fbAppId;
    }

    /**
     * 
     * @param fbAppId
     *     The fb:app_id
     */
    public void setFbAppId(String fbAppId) {
        this.fbAppId = fbAppId;
    }

}
