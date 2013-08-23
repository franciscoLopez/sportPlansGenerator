package png.to.jpg;

import java.io.File;

public class PNGToJPG {

	private final static String _sDirectorio = "C:\\Users\\Francisco\\Desktop\\PFC\\Spring3MVC\\src\\main\\webapp\\images\\storage";
	
	private static int i = 1;

	public static void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;
        
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
            	if(f.getAbsolutePath().endsWith(".png")){
            		String name = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 3);
            		name = name + "jpg";
//            		f.renameTo(new File(name));
            		System.out.println( i + ": " + name);
            		i++;
            	}                
            }
        }
    }

    public static void main(String[] args) {
        walk(_sDirectorio);
    }

}
