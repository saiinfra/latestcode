package com.services.packaging;

import java.util.zip.*;
import java.io.*;

import com.util.ZipHelper;

public class ZipExample {
    public static void main(String[] args){
        ZipHelper zippy = new ZipHelper();
        try {
            zippy.zipDir("SFServerPkgFolder","test.zip");
        } catch(IOException e2) {
            System.err.println(e2);
        }
    }
}

