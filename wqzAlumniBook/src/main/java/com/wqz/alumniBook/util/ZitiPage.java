package com.wqz.alumniBook.util;


import lombok.Data;
import me.ghui.fruit.annotations.Pick;

@Data
public class ZitiPage {

    @Pick(value = "#imgResult",attr = "src")
    private String imgResult;

    public ZitiPage(String imgResult) {
        this.imgResult = imgResult;
    }

    public ZitiPage() {
    }

    public String getImgResult() {
        return imgResult;
    }

    public void setImgResult(String imgResult) {
        this.imgResult = imgResult;
    }

    @Override
    public String toString() {
        return "ZitiPage{" +
                "imgResult='" + imgResult + '\'' +
                '}';
    }
}
