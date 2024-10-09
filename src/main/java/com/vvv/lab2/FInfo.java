package com.vvv.lab2;

public class FInfo{

    private final String nameFile;
    private final String sizeFile;
    private final String sizePict;
    private final String depthPict;
    private final String ratioPict;

    public FInfo (String nameFile,String sizeFile,String sizePict,String depthPict,String ratioPict)
    {
        this.nameFile  = nameFile;
        this.sizeFile  = sizeFile;
        this.sizePict  = sizePict;
        this.depthPict = depthPict;
        this.ratioPict = ratioPict;
    }

    public String getNameFile()  {
        return nameFile;
    }
    public String getSizeFile()  {
        return sizeFile;
    }
    public String getSizePict()  {
        return sizePict;
    }
    public String getDepthPict() {
        return depthPict;  }
    public String getRatioPict(){
        return ratioPict;
    }
//
//
//    public String setSizeFile() {
//        return sizeFile;
//    }
//    public String setNameFile() {
//        return nameFile;
//    }
//    public String setSizePict() {
//        return sizePict;
//    }
//    public String setDepthPict() {
//        return depthPict;
//    }
//    public String setRatioPict() {
//        return ratioPict;
//    }

}
