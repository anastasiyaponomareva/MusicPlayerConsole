import java.util.Random;

public abstract class Component implements Size, Appearance {
    int length;
    int width;
    String theme;
    String font;

    enum Fonts {Monaco, ComicSans, Rosemary, Garamond}

    ;

    enum Themes {Standart, Funky, Modern, Retro, Alternative}

    ;
    public static final Random random = new Random();

    public Component() {
        setStandartSize();
        setStandartAppearance();

    }

    public Component(int length, int width, String theme, String font) {
        this.length = length;
        this.width = width;
        this.theme = theme;
        this.font = font;


    }

    public void setStandartSize() {
        this.length = 400;
        this.width = 400;

    }

    public void setRandomAppearance() {
        this.font = Fonts.values()[random.nextInt(4)].name();
        this.theme = Themes.values()[random.nextInt(4)].name();

    }

    public void setStandartAppearance() {
        this.font = Fonts.Rosemary.toString();
        this.theme = Themes.Standart.toString();
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String getFont() {
        return font;
    }

    @Override
    public String getTheme() {
        return theme;
    }
    @Override
    public void setLength(int length) {
        this.length = length;
    }
    @Override
    public void setWidth(int width) {
        this.width = width;
    }
    @Override
    public void setFont(String font) {
        this.font = font;
    }
    @Override
    public void setTheme(String theme) {
        this.theme = theme;
    }
}


interface Size {
  void setLength(int length);

    void setWidth(int width);

    int getLength();

    int getWidth();

    void setStandartSize();

}

interface Appearance {
    void setTheme(String theme);

    void setFont(String font);

    String getTheme();

    String getFont();

    void setRandomAppearance();

    void setStandartAppearance();

}