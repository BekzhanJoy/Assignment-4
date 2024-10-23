import java.util.ArrayList;
import java.util.List;

class Document {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public DocumentVersion save() {
        return new DocumentVersion(content);
    }

    public void restore(DocumentVersion version) {
        content = version.getContent();
    }
}

class DocumentVersion {
    private final String content;

    public DocumentVersion(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class VersionControl {
    private List<DocumentVersion> versions = new ArrayList<>();

    public void saveVersion(DocumentVersion version) {
        versions.add(version);
    }

    public DocumentVersion getVersion(int index) {
        return versions.get(index);
    }

    public List<DocumentVersion> getAllVersions() {
        return versions;
    }
}

public class Main {
    public static void main(String[] args) {
        Document document = new Document();
        VersionControl versionControl = new VersionControl();

        document.setContent("Version 1");
        versionControl.saveVersion(document.save());

        document.setContent("Version 2");
        versionControl.saveVersion(document.save());

        document.setContent("Version 3");
        versionControl.saveVersion(document.save());

        System.out.println("Current Content: " + document.getContent());

        document.restore(versionControl.getVersion(1));
        System.out.println("Restored to: " + document.getContent());

        document.restore(versionControl.getVersion(0));
        System.out.println("Restored to: " + document.getContent());
    }
}
