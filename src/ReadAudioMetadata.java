import java.io.*;
import java.lang.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.jaudiotagger.*;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/**
 *
 * @author Reece
 */
public class ReadAudioMetadata {

    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Path files = FileSystems.getDefault().getPath("where.mp3");
        
        String[] hi = new String[10];
        hi = searchPath("folder");
        for (String hi1 : hi) {
            System.out.println(hi1);
        }
        System.out.println(searchPath("where.mp3"));
        Audio theFile = new Audio(files);
        theFile.getFile();
        theFile.setTags();
        System.out.println(theFile.fileTag.getFirst(FieldKey.ALBUM));
    }          
    
    public static String[] searchPath(String baseFolder) throws IOException{
        Path folder = FileSystems.getDefault().getPath(baseFolder);
        Stream<Path> audioStream = Files.wa
        String[] audioPath = new String[(int)audioStream.count()];
        audioPath = audioStream.toArray(String[] :: new);

        return audioPath;
       
    } 


    
}


class Audio {
    private AudioFile audioFile;
    private Path filePath;
    public Tag fileTag;
    
    Audio(Path filePath){
        this.filePath = filePath;
    }
    
    public void getFile() { 
        
        try {        
            this.audioFile = AudioFileIO.read(this.filePath.toFile());            
        } catch (CannotReadException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReadOnlyFileException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAudioFrameException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTags() {
        this.fileTag = this.audioFile.getTag();
        Iterator iterator = this.fileTag.getFields();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }       
}

//class MP3 extends Audio{
//
//    public MP3(Path filePath) {
//        
//    }
//    
//    MP3File f = (Mp3File)AudioFileIO.read(testFile);
//    Tag tag        = f.getTag();
//    ID3v1Tag         v1Tag  = (ID3v1Tag)tag;
//    AbstractID3v2Tag v2tag  = f.getID3v2Tag()
//    ID3v24Tag        v24tag = (AbstractID3v2Tag)f.getID3v2TagAsv24();
//
//}
