package pe.bennu.internship.state;

public class AppState {
    private boolean fileGenerated = false;
    private boolean fileSorted = false;

    public boolean canRead() { return fileGenerated; }
    public boolean canSort() { return fileGenerated; }
    public boolean canReadSorted() { return fileSorted; }
    public boolean canSearchBinary() { return fileSorted; }

    public void markGenerated() { fileGenerated = true; fileSorted = false; }
    public void markSorted() { fileSorted = true; }
}