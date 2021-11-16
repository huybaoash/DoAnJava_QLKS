package sample.QLKS_ClassData;

public class ChucVu {
    private String maCV;
    private String tenCV;
    private float hsl;

    public ChucVu(String maCV, String tenCV, float hsl) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.hsl = hsl;
    }


    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public void setHsl(float hsl) {
        this.hsl = hsl;
    }

    public String getMaCV() {
        return maCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public float getHsl() {
        return hsl;
    }
}
