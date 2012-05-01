/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figoo.classes;

/**
 *
 * @author Lada Riha
 */
public class FileInfo {

    private String size;
    private String name;
    private String path;
    private String openWith;
    private String type;
    private String lastModif;
    private boolean isDir;
    private javax.swing.Icon icon;
    private boolean canRead;
    private boolean canWrite;
    private boolean canExecute;
    private String hash;
    private String hash2;
    private String mimeType;

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the isDir
     */
    public boolean isIsDir() {
        return isDir;
    }

    /**
     * @param isDir the isDir to set
     */
    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the openWith
     */
    public String getOpenWith() {
        return openWith;
    }

    /**
     * @param openWith the openWith to set
     */
    public void setOpenWith(String openWith) {
        this.openWith = openWith;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the lastModif
     */
    public String getLastModif() {
        return lastModif;
    }

    /**
     * @param lastModif the lastModif to set
     */
    public void setLastModif(String lastModif) {
        this.lastModif = lastModif;
    }

    /**
     * @return the icon
     */
    public javax.swing.Icon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(javax.swing.Icon icon) {
        this.icon = icon;
    }

    /**
     * @return the canRead
     */
    public boolean isCanRead() {
        return canRead;
    }

    /**
     * @param canRead the canRead to set
     */
    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    /**
     * @return the canWrite
     */
    public boolean isCanWrite() {
        return canWrite;
    }

    /**
     * @param canWrite the canWrite to set
     */
    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    /**
     * @return the canExecute
     */
    public boolean isCanExecute() {
        return canExecute;
    }

    /**
     * @param canExecute the canExecute to set
     */
    public void setCanExecute(boolean canExecute) {
        this.canExecute = canExecute;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
        /**
     * @return the hash
     */
    public String getHash2() {
        return hash2;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash2(String hash) {
        this.hash2 = hash;
    }

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
