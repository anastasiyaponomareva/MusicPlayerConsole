import java.util.Scanner;

/**
 * Created by mac on 17.10.14.
 */


public class Program {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        MusicPlayer myPlayer = new MusicPlayer();
        char choice;
        System.out.println("WELCOME TO SIMPLE MUSIC PLAYER! \n");
        System.out.println("CURRENT SETTINGS ARE: ");
        myPlayer.printProperties();
        System.out.println("WOULD YOU LIKE TO CHANGE SOMETHING? \n" +
                "PRESS R TO RANDOMIZE APPEARANCE, M FOR YOUR OWN SETTINGS, " +
                "SOMETHING ELSE TO SKIP:");

        do {
            System.out.print("YOUR CHOICE IS: ");
            if ((in.hasNextLine())) {
                choice = TryParseChar(in.nextLine());
            }
           else {
                choice = 0;
                System.exit(0);
                break;
            }
        } while (choice == 0);
        choice = Character.toUpperCase(choice);

        switch (choice) {
            case 'R': {
                myPlayer.setRandomAppearance();
                System.out.println("NOW SETTINGS ARE:");
                myPlayer.printProperties();
                break;
            }
            case 'M': {
                Integer length;
                do {
                    System.out.print("WRITE LENGTH: ");
                    length = TryParseInt(in.nextLine());
                } while (length == null || length <= 0);

                Integer width;
                do {
                    System.out.print("WRITE WIDTH: ");
                    width = TryParseInt(in.nextLine());
                } while (width == null || width <= 0);

                System.out.print("WRITE THEME: ");
                String theme = in.nextLine();


                System.out.print("WRITE FONT: ");
                String font = in.nextLine();


                myPlayer.setFont(font);
                myPlayer.setTheme(theme);
                myPlayer.setLength(length);
                myPlayer.setWidth(width);

                System.out.println("NOW SETTINGS ARE:");
                myPlayer.printProperties();

                break;
            }
            default: {
                break;
            }
        }
        System.out.println("HERE IS YOUR PLAYLIST:\n");
        myPlayer.printPlaylist();
        myPlayer.checkWork();
        while (true) {
            try {


                printMenu();
                do {
                    System.out.print("YOUR CHOICE IS: ");
                    if ((in.hasNextLine())) {
                        choice = TryParseChar(in.nextLine());
                    }
                    else {
                        choice = 0;
                        System.exit(0);
                        break;
                    }

                } while (choice == 0);
                choice = Character.toUpperCase(choice);
                switch (choice) {
                    case 'P': {
                        myPlayer.play();
                        myPlayer.checkWork();
                        myPlayer.printPlay();
                        myPlayer.playPlaylistsFromCurrent();
                        break;

                    }

                    case 'N': {
                        myPlayer.nextSong();
                        myPlayer.play();
                        System.out.println("You can start listening to music from song:"
                                + myPlayer.getNowPlaying().SongToDisplay());
                        break;
                    }
                    case 'B': {
                        myPlayer.previousSong();
                        myPlayer.play();
                        System.out.println("You can start listening to music from song:"
                                + myPlayer.getNowPlaying().SongToDisplay());
                        break;
                    }
                    case 'A': {
                        myPlayer.songAdd(createSong());
                        System.out.println("New song was added!");
                        myPlayer.printPlaylist();
                        break;
                    }
                    case 'O': {
                        myPlayer.sortMyList();
                        System.out.println("Your list was sorted!");
                        myPlayer.printPlaylist();
                        break;
                    }
                    case 'R': {
                        Integer numb;
                        do {
                            System.out.print("WRITE NUMBER OF SONG TO REMOVE: ");
                            numb = TryParseInt(in.next());
                        } while (numb == null || numb <= 0 || numb > myPlayer.getMusicPlayList().size());
                        myPlayer.songRemove(numb - 1);
                        System.out.println("Song was removed!");
                        myPlayer.printPlaylist();
                        break;

                    }
                    case 'W': {
                        myPlayer.restart();
                        System.out.println("Playlist is set at its beginning now!");
                        break;
                    }
                    case 'E': {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("YOU HAVE TYPED INCORRECT LETTER!");
                        break;
                    }

                }


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nTYPE THE SUITABLE OPTION:\n" +
                "P-PLAY, B - PREVIOUS SONG, N-NEXT SONG, A - ADD SONG, R - REMOVE SONG, O - SORT SONGS BY NAME AND LENGTH, " +
                "E - EXIT, W - SET PLAYLIST TO THE BEGINNING: \n");
    }

    public static char TryParseChar(String text) {
        char a;
        if (text.length() == 1) {

            a = text.charAt(0);
        } else {
            a = 0;
        }
        return a;
    }

    public static Song createSong() {
        Scanner in = new Scanner(System.in);
        System.out.print("WRITE  SONG NAME: ");
        String sname = in.nextLine();

        System.out.print("WRITE ARTIST: ");
        String sartist = in.nextLine();

        Integer slength;
        do {
            System.out.print("WRITE LENGTH IN SEC.: ");
            slength = TryParseInt(in.nextLine());
        } while (slength == null || slength <= 0);

        System.out.print("WRITE GENRE: ");
        String sgenre = in.nextLine();
        return new Song(sname, sartist, sgenre, slength);

    }


    /**
     * Imitating C#-like tryParse
     */
    public static Integer TryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
