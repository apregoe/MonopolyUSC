package MonopolyGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import MonopolyLibrary.MonopolyLibrary;

// helpful data structures!

public class GameHelpers {
	
	private static final Map<Integer, String> Controllers = new HashMap<Integer, String>();
	private static final Map<Integer, String> Properties = new HashMap<Integer, String>();
	private static final Map<Integer, String> Roads = new HashMap<>();
	private static final Map<Integer, String> StudentChest = new HashMap<Integer, String>();
	private static final Map<Integer, String> Chance = new HashMap<Integer, String>();
	private static final Map<Integer, String> Tokens = new HashMap<>();
	private static final Map<Integer, Image> controlImages = new HashMap<>();
	private static final Map<Integer, Image> propertyImages = new HashMap<>();
	private static final Map<Integer, Image> roadImages = new HashMap<>();
	private static final Map<Integer, Image> tokenImages = new HashMap<>();
	private static Image jailImage;
    private static Image studentChestImage;
    private static Image chanceImage;

    static {
		Controllers.put( 0, "GO" );
		Controllers.put( 1, "Tuition Due" );
		Controllers.put( 2, "Just Visiting / SJACS" );
		Controllers.put( 3, "Campus Cruiser" );
		Controllers.put( 4, "Free Parking" );
		Controllers.put( 5, "USC Bookstore" );
		Controllers.put( 6, "Go to SJACS" );
		Controllers.put( 7, "Mandatory Fee" );

		try{
			controlImages.put(0, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/GO.png")));
			controlImages.put(1, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/TuitionDue.jpeg"))); //TODO Update these
			controlImages.put(2, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/SJACS.png"))); //TODO Update these
			controlImages.put(3, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/campus_cruiser.png")));
			controlImages.put(4, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/FreeParking.png")));
			controlImages.put(5, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/bookstore.jpeg")));
			controlImages.put(6, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/ToSjacs.jpeg")));
			controlImages.put(7, ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/MandatoryFee.jpeg"))); //TODO UPDATE IMAGES
		}catch(IOException ioe){
			System.out.print("IO Exception: ");
			ioe.printStackTrace();
		}


		Properties.put( 0, "Doheny Library" );
		Properties.put( 1, "Leavey Library" );
		Properties.put( 2, "Webb Tower" );
		Properties.put( 3, "Cardinal Gardens" );
		Properties.put( 4, "Troy East" );
		Properties.put( 5, "Cromwell Field" );
		Properties.put( 6, "IM Field" );
		Properties.put( 7, "Lyon Center" );
		Properties.put( 8, "Tommy's Place" );
		Properties.put( 9, "Ground Zero" );
		Properties.put( 10, "Bovard Auditorium" );
		Properties.put( 11, "EVK" );
		Properties.put( 12, "Cafe 84" );
		Properties.put( 13, "Parkside" );
		Properties.put( 14, "EEB" );
		Properties.put( 15, "SAL" );
		Properties.put( 16, "RTH" );
		Properties.put( 17, "Alumni House" );
		Properties.put( 18, "Fisher House" );
		Properties.put( 19, "Trophy Room" );
		Properties.put( 20, "USC Village" );
		Properties.put( 21, "Memorial Coliseum" );
		
		try{
			propertyImages.put( 0,  ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/doheny.jpeg")));
			propertyImages.put( 1,  ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/leavy.jpeg")));
			propertyImages.put( 2,  ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/webb.jpeg")));
			propertyImages.put( 3, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/century.jpeg")));
			propertyImages.put( 4, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/troy.jpeg")));
			propertyImages.put( 5, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/cromwell.jpeg")));
			propertyImages.put( 6, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/BrittinghamField.jpeg")));
			propertyImages.put( 7, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/lyon_center.jpeg")));
			propertyImages.put( 8, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/tommys_place.jpeg")));
			propertyImages.put( 9, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/ground_zero.jpeg")));
			propertyImages.put( 10, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/bovard.jpeg")));
			propertyImages.put( 11, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/evk.jpeg")));
			propertyImages.put( 12, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/cafe84.jpeg")));
			propertyImages.put( 13, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/parkside.jpeg")));
			propertyImages.put( 14, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/eeb.jpeg")));//TODO: FIX
			propertyImages.put( 15, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/sal.jpeg")));
			propertyImages.put( 16, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/rth.jpeg")));
			propertyImages.put( 17, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/alumni_house.jpeg")));
			propertyImages.put( 18, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/fisher_museum.jpeg")));
			propertyImages.put( 19, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/trophy_room.jpeg")));
			propertyImages.put( 20, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/uscvillage.jpeg")));
			propertyImages.put( 21, ImageIO.read(new File(MonopolyLibrary.imagePath +  "properties/LA_coliseum.jpeg")));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		
		Roads.put( 0, "Exposition Blvd" );
		Roads.put( 1, "Vermont St" );
		Roads.put( 2, "Jefferson Blvd" );
		Roads.put( 3, "Figueroa St" );

		try{
			roadImages.put(0, ImageIO.read(new File(MonopolyLibrary.imagePath +  "roads/Exposition.jpeg")));
			roadImages.put(1, ImageIO.read(new File(MonopolyLibrary.imagePath +  "roads/vermont.jpeg")));
			roadImages.put(2, ImageIO.read(new File(MonopolyLibrary.imagePath +  "roads/Jefferson.jpeg")));
			roadImages.put(3, ImageIO.read(new File(MonopolyLibrary.imagePath +  "roads/figueroa.jpeg")));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}

        try{
            studentChestImage = ImageIO.read(new File(MonopolyLibrary.imagePath +  "specialLocations/students_chest2.jpeg"));
            chanceImage = ImageIO.read(new File(MonopolyLibrary.imagePath +  "specialLocations/chancetile_south.jpg"));
        	jailImage = ImageIO.read(new File(MonopolyLibrary.imagePath +  "controllers/SJACS.png"));
		}catch(IOException ioe){
            ioe.printStackTrace();
        }



		Tokens.put(0, "blimp");
		Tokens.put(1, "football");
		Tokens.put(2, "helmet");
		Tokens.put(3, "segway");
		Tokens.put(4, "sword");
		Tokens.put(5, "torch");
		Tokens.put(6, "traveller");
		Tokens.put(7, "trojan");
		try{
			tokenImages.put(0, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/blimp.png")));
			tokenImages.put(1, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/football.png")));
			tokenImages.put(2, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/helmet.gif")));
			tokenImages.put(3, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/segway.png")));
			tokenImages.put(4, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/sword.gif")));
			tokenImages.put(5, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/torch.gif")));
			tokenImages.put(6, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/traveller.gif")));
			tokenImages.put(7, ImageIO.read(new File(MonopolyLibrary.imagePath + "pawns/trojan.gif")));
		}catch (IOException ioe){
			ioe.printStackTrace();
		}


	}

	public static Image getTokenImage(int id){
		return tokenImages.get(id);
	}

	public static String getTokenName(int id){
		return Tokens.get(id);
	}

	public static Image getJailImage(){
		return jailImage;
	}
	
	public static String getControlName( int id ) {
		return Controllers.get( id );
	}
	
	public static String getPropertyName( int id ) {
		return Properties.get( id );
	}
	
	public static String getRoadName( int id ) {
		return Roads.get( id );
	}
	
	public static Image getRoadImage(int id){
		return roadImages.get(id);
	}

	public static Image getPropertyImage(int id){
        return propertyImages.get(id);
    }

    public static Image getControlImages(int id){
        return controlImages.get(id);
    }

    public static Image getStudentChestImage(){
        return studentChestImage;
    }
    public static Image getChanceImage(){
        return chanceImage;
    }
}