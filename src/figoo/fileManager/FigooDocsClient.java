/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figoo.fileManager;

import com.google.gdata.client.DocumentQuery;
import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.FolderEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import figoo.DownloadSingleGDocDialog;
import figoo.ErrorDialog;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lada Riha
 */
public class FigooDocsClient {

    private static final String DOC_OLD_BASE_URL = "https://docs.google.com/feeds/download/documents/";
    private static final String SPR_OLD_BASE_URL = "https://spreadsheets.google.com/feeds/download/spreadsheets/";
    private static final String PRE_OLD_BASE_URL = "https://docs.google.com/feeds/download/presentations/";
    private static final String DOC_NEW_BASE_URL = "https://docs.google.com/feeds/download/documents/export/";
    private static final String SPR_NEW_BASE_URL = "https://spreadsheets.google.com/feeds/download/spreadsheets/export/";
    private static final String PRE_NEW_BASE_URL = "https://docs.google.com/feeds/download/presentations/export/";

    /**
     * Returns instance of DocsService with given credentials
     * @param username full username (wiht @gmail.com)
     * @param password password
     * @return
     * @throws AuthenticationException
     */
    public static DocsService logDocs(String username, String password) throws AuthenticationException {
        DocsService myService = new DocsService("figoo");
        myService.setUserCredentials(username, password);
        return myService;
    }

    /**
     *  Returns instance of DocsService with given credentials
     * @param u  full username (wiht @gmail.com)
     * @param p password
     * @return
     * @throws AuthenticationException
     */
    public static SpreadsheetService logSpreads(String u, String p) throws AuthenticationException {
        SpreadsheetService myService = new SpreadsheetService("figoo");
        myService.setUserCredentials(u, p);
        return myService;
    }

    /**
     * Returns content of root folder in Google Documents
     * @param client
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static ArrayList<DocumentListEntry> getRootContent(DocsService client) throws MalformedURLException, IOException, ServiceException {
        URL feedUri = new URL("https://docs.google.com/feeds/default/private/full/folder%3Aroot/contents/-/folder");
        DocumentListFeed feed = client.getFeed(feedUri, DocumentListFeed.class);
        DocumentListFeed allEntries = new DocumentListFeed();
        ArrayList<DocumentListEntry> files = new ArrayList<DocumentListEntry>();
        boolean tick = false;
        boolean cont = true;
        while (cont) {
            allEntries.getEntries().addAll(feed.getEntries());
            if (feed.getNextLink() != null) {
                tick = true;
                feed = client.getFeed(new URL(feed.getNextLink().getHref()), DocumentListFeed.class);
            } else {
                cont = false;
            }
            if (feed.getNextLink() == null && tick) {
                cont = false;
                tick = false;
                allEntries.getEntries().addAll(feed.getEntries());
            }
        }

        for (int i = 0; i < allEntries.getEntries().size(); i++) {// browse all folders
            DocumentListEntry entry = allEntries.getEntries().get(i);
            files.add(entry);
        }

        DocumentQuery query = new DocumentQuery(new URL("https://docs.google.com/feeds/default/private/full"));
        feedUri = new URL("https://docs.google.com/feeds/default/private/full/");
        feed = client.getFeed(feedUri, DocumentListFeed.class);
        DocumentListFeed allEntries2 = new DocumentListFeed();
        cont = true;
        tick = false;
        while (cont) {
            allEntries2.getEntries().addAll(feed.getEntries());
            if (feed.getNextLink() != null) {
                tick = true;
                feed = client.getFeed(new URL(feed.getNextLink().getHref()), DocumentListFeed.class);
            } else {
                cont = false;
            }
            if (feed.getNextLink() == null && tick) {
                cont = false;
                tick = false;
                allEntries2.getEntries().addAll(feed.getEntries());
            }
        }


        for (DocumentListEntry entry : allEntries2.getEntries()) { // print rest files
            if (!entry.isTrashed() && entry.getParentLinks().isEmpty()) {
                files.add(entry);
            }
        }
        return files;
    }

    /**
     * Returns content of folder specified by resourceID
     * @param resourceID id of folder to be listed
     * @param client
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static ArrayList<DocumentListEntry> getFolderContent(String resourceID, DocsService client) throws MalformedURLException, IOException, ServiceException {
        resourceID = resourceID.substring(resourceID.indexOf(":") + 1);
        URL foldersFeedUri = new URL("https://docs.google.com/feeds/default/private/full/folder%3A" + resourceID + "/contents");
        // System.out.println("URL " + foldersFeedUri.toString());
        DocumentListFeed feed = client.getFeed(foldersFeedUri, DocumentListFeed.class);
        DocumentListFeed allEntries = new DocumentListFeed();
        ArrayList<DocumentListEntry> files = new ArrayList<DocumentListEntry>();
        boolean tick = false;
        boolean cont = true;
        while (cont) {
            allEntries.getEntries().addAll(feed.getEntries());
            if (feed.getNextLink() != null) {
                tick = true;
                feed = client.getFeed(new URL(feed.getNextLink().getHref()), DocumentListFeed.class);
            } else {
                cont = false;
            }
            if (feed.getNextLink() == null && tick) {
                cont = false;
                tick = false;
                allEntries.getEntries().addAll(feed.getEntries());
            }
        }
        for (int i = 0; i < allEntries.getEntries().size(); i++) {// browse all folders
            DocumentListEntry entry = allEntries.getEntries().get(i);
            files.add(entry);
        }
        return files;
    }

    /**
     * Return root folder structure as a HashMap where keys are folder's resource IDs and values are parent folder's resource ID
     * @param client
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static Map<String, String> getStructure(DocsService client) throws MalformedURLException, IOException, ServiceException {
        Map<String, String> struct = new HashMap<String, String>();
        URL feedUri = new URL("https://docs.google.com/feeds/default/private/full/folder%3Aroot/contents/-/folder");
        DocumentListFeed feed = client.getFeed(feedUri, DocumentListFeed.class);
        DocumentListFeed allEntries = new DocumentListFeed();
        ArrayList<DocumentListEntry> files = new ArrayList<DocumentListEntry>();
        boolean tick = false;
        boolean cont = true;
        while (cont) {
            allEntries.getEntries().addAll(feed.getEntries());
            if (feed.getNextLink() != null) {
                tick = true;
                feed = client.getFeed(new URL(feed.getNextLink().getHref()), DocumentListFeed.class);
            } else {
                cont = false;
            }
            if (feed.getNextLink() == null && tick) {
                cont = false;
                tick = false;
                allEntries.getEntries().addAll(feed.getEntries());
            }
        }

        for (int i = 0; i < allEntries.getEntries().size(); i++) {// browse all folders
            DocumentListEntry entry = allEntries.getEntries().get(i);
            if (entry.getType().equals("folder")) {
                if (entry.getParentLinks() == null || entry.getParentLinks().isEmpty()) {
                    struct.put(entry.getResourceId(), "folder:root");
                    struct.putAll(getInnerStructure(entry.getResourceId(), client));
                }
            }
        }
        return struct;
    }

    /**
     * Return  folder structure as a HashMap where keys are folder's resource IDs and values are parent folder's resource ID
     * @param resourceID id of folder to be searched
     * @param client
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static Map<String, String> getInnerStructure(String resourceID, DocsService client) throws MalformedURLException, IOException, ServiceException {
        Map<String, String> struct = new HashMap<String, String>();

        resourceID = resourceID.substring(resourceID.indexOf(":") + 1);
        URL foldersFeedUri = new URL("https://docs.google.com/feeds/default/private/full/folder%3A" + resourceID + "/contents");
        DocumentListFeed feed = client.getFeed(foldersFeedUri, DocumentListFeed.class);
        DocumentListFeed allEntries = new DocumentListFeed();
        boolean tick = false;
        boolean cont = true;
        while (cont) {
            allEntries.getEntries().addAll(feed.getEntries());
            if (feed.getNextLink() != null) {
                tick = true;
                feed = client.getFeed(new URL(feed.getNextLink().getHref()), DocumentListFeed.class);
            } else {
                cont = false;
            }
            if (feed.getNextLink() == null && tick) {
                cont = false;
                tick = false;
                allEntries.getEntries().addAll(feed.getEntries());
            }
        }

        for (int i = 0; i < allEntries.getEntries().size(); i++) {// browse all folders
            DocumentListEntry entry = allEntries.getEntries().get(i);
            if (entry.getType().equals("folder")) {
                struct.put(entry.getResourceId(), resourceID);
                struct.putAll(getInnerStructure(entry.getResourceId(), client));
            }
        }
        return struct;
    }

    /**
     * Returns file type of give file
     * @param docs
     * @param resourceID
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String getFileType(DocsService docs, String resourceID) throws MalformedURLException, IOException, ServiceException {
        URL url = new URL("https://docs.google.com/feeds/default/private/full/" + resourceID);
        DocumentListEntry entry = docs.getEntry(url, DocumentListEntry.class);
        return entry.getType();
    }

    /**
     * Returns file name of give file
     * @param resourceID
     * @param docs
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String getFileName(String resourceID, DocsService docs) throws MalformedURLException, IOException, ServiceException {
        URL url = new URL("https://docs.google.com/feeds/default/private/full/" + resourceID);
        DocumentListEntry entry = docs.getEntry(url, DocumentListEntry.class);
        return entry.getTitle().getPlainText();
    }

    /**
     * Downloads file
     * @param resourceID  file to be downloaded
     * @param docs
     * @param format export format (i.e. pdf, doc...)
     * @param to target folder
     * @param docType file type (folder, file, document, presentation...)
     * @param spread
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void downloadFile(String resourceID, DocsService docs, String format, String to, String docType, SpreadsheetService spread) throws MalformedURLException, IOException, ServiceException {

        URL url = new URL("https://docs.google.com/feeds/default/private/full/" + resourceID);
        DocumentListEntry entry = docs.getEntry(url, DocumentListEntry.class);
        MediaContent mc = (MediaContent) entry.getContent();
        //      System.out.println("URI " + mc.getUri());
        String docId = resourceID.substring(resourceID.lastIndexOf(":") + 1);
        String exportUrl = "";
        String filename = FigooDocsClient.getFileName(docId, docs);
        filename = filename + "." + format;
        to = to + System.getProperty("file.separator") + filename;


        if (docType.equals("spreadsheet")) {
            UserToken docsToken = (UserToken) docs.getAuthTokenFactory().getAuthToken();
            UserToken spreadsheetsToken = (UserToken) spread.getAuthTokenFactory().getAuthToken();
            docs.setUserToken(spreadsheetsToken.getValue());
            try {
                try {
                    downloadNewSpreadsheet(docId, format, docType, to, docs, spread);
                } catch (Exception e) {
                    downloadOldSpreadsheet(docId, format, docType, to, docs, spread);
                }
            } catch (Exception e) {
                e.printStackTrace();
                docs.setUserToken(docsToken.getValue());
            }
            docs.setUserToken(docsToken.getValue());
        } else if (docType.equals("document")) {
            try {
                downloadNewDocument(docId, format, docType, to, docs);
            } catch (Exception e) {
                downloadOldDocument(docId, format, docType, to, docs);
            }
        } else if (docType.equals("presentation")) {
            try {
                downloadNewPresentation(docId, format, docType, to, docs);
            } catch (Exception e) {
                downloadOldPresentation(docId, format, docType, to, docs);
            }
        }
    }

    /**
     * Download pdf file
     * @param resourceID
     * @param docs
     * @param format useless parameter
     * @param to target folder
     * @param docType useless parameter
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void downloadPdfFile(String resourceID, DocsService docs, String format, String to, String docType) throws MalformedURLException, IOException, ServiceException {
        URL url = new URL("https://docs.google.com/feeds/default/private/full/" + resourceID);
        DocumentListEntry entry = docs.getEntry(url, DocumentListEntry.class);
        MediaContent mc = (MediaContent) entry.getContent();
        String exportUrl = mc.getUri();
        String docId = resourceID.substring(resourceID.lastIndexOf(":") + 1);
        String filename = FigooDocsClient.getFileName(docId, docs);
        filename = filename + ".pdf";
        to = to + System.getProperty("file.separator") + filename;
        FileManager.downloadFile(exportUrl, to, docs);
    }

    /**
     * Downloads common file
     * @param resourceID
     * @param docs
     * @param format useless parameter
     * @param to target folder
     * @param docType useless parameter
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void downloadRegularFile(String resourceID, DocsService docs, String format, String to, String docType) throws MalformedURLException, IOException, ServiceException {
        URL url = new URL("https://docs.google.com/feeds/default/private/full/" + resourceID);
        DocumentListEntry entry = docs.getEntry(url, DocumentListEntry.class);
        MediaContent mc = (MediaContent) entry.getContent();
        String exportUrl = mc.getUri();
        String docId = resourceID.substring(resourceID.lastIndexOf(":") + 1);
        String filename = FigooDocsClient.getFileName(docId, docs);
        to = to + System.getProperty("file.separator") + filename;
        FileManager.downloadFile(exportUrl, to, docs);
    }

    private static void downloadNewPresentation(String docId, String format, String docType, String to, DocsService docs) throws MalformedURLException, IOException, ServiceException {
        String exportUrl = PRE_NEW_BASE_URL + "Export?id=" + docId + "&format=" + format;
        if (format.equals("csv") || format.equals("tsv")) {
            exportUrl += "&gid=0";
        }
        FileManager.downloadFile(exportUrl, to, docs);
    }

    private static void downloadOldPresentation(String docId, String format, String docType, String to, DocsService docs) throws MalformedURLException, IOException, ServiceException {
        String exportUrl = PRE_OLD_BASE_URL + "Export?docId=" + docId + "&exportFormat=" + format;
        if (format.equals("csv") || format.equals("tsv")) {
            exportUrl += "&gid=0";
        }
        FileManager.downloadFile(exportUrl, to, docs);
    }

    private static void downloadNewDocument(String docId, String format, String docType, String to, DocsService docs) throws MalformedURLException, IOException, ServiceException {
        String exportUrl = DOC_NEW_BASE_URL + "Export?id=" + docId + "&format=" + format;
        if (format.equals("csv") || format.equals("tsv")) {
            exportUrl += "&gid=0";
        }
        FileManager.downloadFile(exportUrl, to, docs);
    }

    private static void downloadOldDocument(String docId, String format, String docType, String to, DocsService docs) throws MalformedURLException, IOException, ServiceException {
        String exportUrl = DOC_OLD_BASE_URL + "Export?docId=" + docId + "&exportFormat=" + format;
        if (format.equals("csv") || format.equals("tsv")) {
            exportUrl += "&gid=0";
        }
        FileManager.downloadFile(exportUrl, to, docs);
    }

    private static void downloadNewSpreadsheet(String docId, String format, String docType, String to, DocsService docs, SpreadsheetService spread) throws MalformedURLException, IOException, ServiceException {
        String exportUrl = SPR_NEW_BASE_URL + "Export?id=" + docId + "&format=" + format;
        if (format.equals("csv") || format.equals("tsv")) {
            exportUrl += "&gid=0";
        }
        FileManager.downloadFile(exportUrl, to, docs, spread);
    }

    private static void downloadOldSpreadsheet(String docId, String format, String docType, String to, DocsService docs, SpreadsheetService spread) throws MalformedURLException, IOException, ServiceException {
        String exportUrl = SPR_OLD_BASE_URL + "Export?key=" + docId + "&exportFormat=" + format;
        if (format.equals("csv") || format.equals("tsv")) {
            exportUrl += "&gid=0";
        }
        FileManager.downloadFile(exportUrl, to, docs, spread);
    }

    /**
     * Downloads folders recursively
     * @param resourceID
     * @param docs
     * @param spread
     * @param to target folder
     * @param format export format
     * @param docType file type (document, folder...)
     * @param dp
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void recursiveDownload(String resourceID, DocsService docs, SpreadsheetService spread, String to, String format, String docType, DownloadSingleGDocDialog dp) throws MalformedURLException, IOException, ServiceException {
        // browse children
        //FORMAT = {"doc|xls|ppt", "pdf|pdf|pdf", "odt|ods|ppt"};
        String docId = resourceID.substring(resourceID.lastIndexOf(":") + 1);
        String filename = FigooDocsClient.getFileName(docId, docs);
        File folder = new File(to + System.getProperty("file.separator") + filename);
        folder.mkdirs();

        ArrayList<DocumentListEntry> content = FigooDocsClient.getFolderContent(resourceID, docs);
        DocumentListEntry entry;
        String type;
        for (int i = 0; i < content.size(); i++) {
            entry = content.get(i);
            if (entry.getType().equalsIgnoreCase("folder")) {
                recursiveDownload(entry.getResourceId(), docs, spread, folder.getAbsolutePath(), format, docType, dp);
            } else {
                try {
                    type = entry.getType();
                    dp.getjLabel3().setText(entry.getTitle().getPlainText());
                    if (type.equals("file")) {
                        FigooDocsClient.downloadRegularFile(entry.getResourceId(), docs, format, folder.getAbsolutePath(), docType);
                    } else if (type.equals("pdf")) {
                        FigooDocsClient.downloadPdfFile(entry.getResourceId(), docs, format, folder.getAbsolutePath(), docType);
                    } else {
                        String document = (format.substring(0, format.indexOf("|")));
                        String spreadsheet = (format.substring(format.indexOf("|") + 1, format.lastIndexOf("|")));
                        String presentation = (format.substring(format.lastIndexOf("|") + 1));
                        if (type.equals("document")) {
                            FigooDocsClient.downloadFile(entry.getResourceId(), docs, document, folder.getAbsolutePath(), type, spread);
                        } else if (type.equals("spreadsheet")) {
                            FigooDocsClient.downloadFile(entry.getResourceId(), docs, spreadsheet, folder.getAbsolutePath(), type, spread);
                        } else {
                            FigooDocsClient.downloadFile(entry.getResourceId(), docs, presentation, folder.getAbsolutePath(), type, spread);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "recursiveDownload", ex.getMessage());
                    ed.setVisible(true);
                }
            }
        }
    }

    /**
     * Deletes file
     * @param docs
     * @param resourceId
     * @param trash true if move file to trash, false if delete file pernamently
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void deleteFile(DocsService docs, String resourceId, boolean trash) throws MalformedURLException, IOException, ServiceException {
        if (trash) {
            docs.delete(new URL("https://docs.google.com/feeds/default/private/full/" + resourceId), "*");
        } else {
            URL url = new URL("https://docs.google.com/feeds/default/private/full/" + resourceId);
            DocumentListEntry entry = docs.getEntry(url, DocumentListEntry.class);
            docs.delete(new URL(entry.getEditLink().getHref() + "?delete=true"), entry.getEtag());
        }
    }

    /**
     * Renames file
     * @param docs
     * @param resourceID
     * @param text new file name
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void rename(DocsService docs, String resourceID, String text) throws MalformedURLException, IOException, ServiceException {
        URL url = new URL("https://docs.google.com/feeds/default/private/full/" + resourceID);
        DocumentListEntry entry = docs.getEntry(url, DocumentListEntry.class);
        entry.setTitle(new PlainTextConstruct(text));
        entry.update();
    }

    /**
     * Creates folder
     * @param docs
     * @param name folder name
     * @param parent parent folder
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void makeFolder(DocsService docs, String name, String parent) throws MalformedURLException, IOException, ServiceException {
        DocumentListEntry newEntry = new FolderEntry();
        newEntry.setTitle(new PlainTextConstruct(name));
        URL feedUrl = new URL("https://docs.google.com/feeds/default/private/full/");

        if (!parent.equals("folder:root")) {
            URL url = new URL("https://docs.google.com/feeds/default/private/full/" + parent);
            DocumentListEntry destFolderEntry = docs.getEntry(url, DocumentListEntry.class);
            String destFolderUri = ((MediaContent) destFolderEntry.getContent()).getUri();
            DocumentListEntry newTmp = new FolderEntry();
            newTmp.setId(newEntry.getId());
            docs.insert(new URL(destFolderUri), newEntry);
        } else {
            docs.insert(feedUrl, newEntry);
        }
    }

    /**
     * Creates new folder and returns its resource ID
     * @param docs
     * @param name
     * @param parent
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String makeFolderGetResourceId(DocsService docs, String name, String parent) throws MalformedURLException, IOException, ServiceException {
        DocumentListEntry newEntry = new FolderEntry();
        newEntry.setTitle(new PlainTextConstruct(name));
        URL feedUrl = new URL("https://docs.google.com/feeds/default/private/full/");
        if (!parent.equals("root")) {
            URL url = new URL("https://docs.google.com/feeds/default/private/full/" + parent);
            DocumentListEntry destFolderEntry = docs.getEntry(url, DocumentListEntry.class);
            String destFolderUri = ((MediaContent) destFolderEntry.getContent()).getUri();
            DocumentListEntry newTmp = new FolderEntry();
            newTmp.setId(newEntry.getId());
            return docs.insert(new URL(destFolderUri), newEntry).getResourceId();
        } else {
            return docs.insert(feedUrl, newEntry).getResourceId();
        }

    }

    /**
     * Upload file to Google Documents
     * @param file
     * @param from
     * @param toResourceId
     * @param docs
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void uploadFile(File file, String from, String toResourceId, DocsService docs) throws MalformedURLException, IOException, ServiceException {
        if (!toResourceId.equals("root")) {
            URL url = new URL("https://docs.google.com/feeds/default/private/full/" + toResourceId);
            DocumentListEntry folderEntry = docs.getEntry(url, DocumentListEntry.class);
            String destFolderUrl = ((MediaContent) folderEntry.getContent()).getUri();
            DocumentListEntry newDocument = new DocumentListEntry();
            String mimeType = DocumentListEntry.MediaType.fromFileName(file.getName()).getMimeType();
            newDocument.setFile(file, mimeType);
            String title = file.getName().substring(0, file.getName().lastIndexOf("."));
            newDocument.setTitle(new PlainTextConstruct(title));
            URL u = new URL(destFolderUrl);
            docs.insert(u, newDocument);
        } else {
            URL url = new URL("https://docs.google.com/feeds/default/private/full/");
            DocumentListEntry newDocument = new DocumentListEntry();
            String mimeType = DocumentListEntry.MediaType.fromFileName(file.getName()).getMimeType();
            newDocument.setFile(file, mimeType);
            String title = file.getName().substring(0, file.getName().lastIndexOf("."));
            newDocument.setTitle(new PlainTextConstruct(title));
            docs.insert(url, newDocument);
        }
    }

    /**
     * Returns structure of folders and files in Google Documents
     * @param dir  where to start (folder's id (full URL) or just docs for root folder)
     * @param docs instance of DocsService
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String structureToString(String dir, DocsService docs) throws MalformedURLException, IOException, ServiceException {
        StringBuffer sb = new StringBuffer();
        if (dir.equals("/docs")) {
            sb.append("Google Documents" + System.getProperty("line.separator"));
            ArrayList<DocumentListEntry> list = FigooDocsClient.getRootContent(docs);
            for (int i = 0; i < list.size(); i++) {
                sb.append("   " + list.get(i).getTitle().getPlainText() + "   (" + list.get(i).getType() + ")");
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        } else {
            String title = FigooDocsClient.getFileName(dir, docs);
            ArrayList<DocumentListEntry> list = FigooDocsClient.getFolderContent(dir, docs);
            sb.append("Google Documents/" + title + System.getProperty("line.separator"));
            for (int i = 0; i < list.size(); i++) {
                sb.append("   " + list.get(i).getTitle().getPlainText() + "   (" + list.get(i).getType() + ")");
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        }
    }

    /**
     * Returns structure of folders and files in Google Documents
     * @param dir  where to start (folder's id (full URL) or just docs for root folder)
     * @param depth depth of search
     * @param indention text indention
     * @param include whether include text header
     * @param docs instance of DocsService
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String structureToString(String dir, int depth, String indention, boolean include, DocsService docs) throws MalformedURLException, IOException, ServiceException {

        StringBuffer sb = new StringBuffer();
        if (depth >= 0) {
            indention = indention + "  ";
            int newDepth = depth - 1;
            String tt = "";
            if (dir.equals("docs")) {
                sb.append("Google Documents" + System.getProperty("line.separator"));
                ArrayList<DocumentListEntry> list = FigooDocsClient.getRootContent(docs);

                for (int i = 0; i < list.size(); i++) {
                    tt = list.get(i).getType();
                    sb.append(indention + list.get(i).getTitle().getPlainText() + "   (" + tt + ")");
                    sb.append(System.getProperty("line.separator"));
                    if (tt.equals("folder")) {
                        String s = FigooDocsClient.structureToString(list.get(i).getResourceId(), newDepth, indention, false, docs);
                        sb.append(s);
                    }
                }
                return sb.toString();
            } else {
                String title = FigooDocsClient.getFileName(dir, docs);
                if (include) {
                    sb.append("Google Documents/" + title + System.getProperty("line.separator"));
                }

                ArrayList<DocumentListEntry> list = FigooDocsClient.getFolderContent(dir, docs);
                for (int i = 0; i < list.size(); i++) {
                    tt = list.get(i).getType();
                    sb.append(indention + list.get(i).getTitle().getPlainText() + "   (" + tt + ")");
                    sb.append(System.getProperty("line.separator"));
                    if (tt.equals("folder")) {
                        String s = FigooDocsClient.structureToString(list.get(i).getResourceId(), newDepth, indention, false, docs);
                        sb.append(s);
                    }
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }
}
