
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Elevator1 {
    
    public int id=1;
    public static int currentFloorElevator1=0;
    public static int currentFloorElevator2=0;
    public static int currentFloorElevator3=0;
    public static int direction = 1;   // 1 refers UP, 0 refers Down...
    public int limit = 10;
    public static int situation = 0;
    public static int goingFirstFloorPeople=0;
    public static int goingSecondFloorPeople=0;
    public static int goingThirdFloorPeople=0;
    public static int goingFourthFloorPeople=0;
    public static int exitPeopleCount = 0;
    public static boolean elevator2Working = false;
    public static boolean elevator3Working = false;
    public static boolean elevator4Working = false;
    public static boolean elevator5Working = false;
    public static int totalPeople = 0;
    
    
    //Login login = new Login();
    public static ArrayList<Integer> destinationFloors = new ArrayList<Integer>();
    public static ArrayList<Integer> destinationPeople = new ArrayList<Integer>();
    
    
    
    public void arrayListOptimizationFloor(ArrayList<Integer> arraylist){

        for(int i=0; i<arraylist.size(); i++){   // Removes same elements...
            for(int j = i+1; j<arraylist.size(); j++){
                if(arraylist.get(i) == arraylist.get(j)){
                    arraylist.remove(j);
                }
            }
        }

        for(int i=0; i<arraylist.size(); i++){   // Sorting lower to higher...
            for(int j = i+1; j<arraylist.size(); j++){
                if(arraylist.get(i) > arraylist.get(j)){
                    int temp = arraylist.get(j);
                    arraylist.set(j, arraylist.get(i));
                    arraylist.set(i, temp);
                }
            }
        }
        
    }
    public static void destinationPeoplePrepare(){
        destinationPeople.add(0);
        destinationPeople.add(0);
        destinationPeople.add(0);
        destinationPeople.add(0);
    }
    public void arrayListOptimizationPeople(int people, int floor){ // Prepares how people we lift for each floor...
        
        if(floor == 1){
            destinationPeople.set(0,destinationPeople.get(0)+people);
            goingFirstFloorPeople+=people;
        }
        else if(floor == 2){
            destinationPeople.set(1,destinationPeople.get(1)+people);
            goingSecondFloorPeople +=people;
        }
        else if(floor == 3){
            destinationPeople.set(2,destinationPeople.get(2)+people);
            goingThirdFloorPeople+=people;
        }
        else if(floor == 4){
            destinationPeople.set(3,destinationPeople.get(3)+people);
            goingFourthFloorPeople+=people;
        }
        
    }
    
    
     public static BlockingQueue<Integer> loginPeopleCount = new ArrayBlockingQueue<Integer>(1000);
     public static BlockingQueue<Integer> loginFloor = new ArrayBlockingQueue<Integer>(1000);
    Random random = new Random();
    
    
    public   void login(){
        while(true){
            int randomLoginPeopleCount = random.nextInt(10)+1;
            int randomFloor = random.nextInt(4)+1;
       
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
            try {
                loginPeopleCount.put(randomLoginPeopleCount);
                loginFloor.put(randomFloor);
                System.out.println("Yeni giren kişi sayısı: "+randomLoginPeopleCount+" Gidecekleri kat: "+randomFloor);
            } catch (InterruptedException ex) {
                Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        
        }
     
     
            
    }
    
    public static BlockingQueue<Integer> firstFloorExitQueue = new ArrayBlockingQueue<Integer>(250);
    public static BlockingQueue<Integer> secondFloorExitQueue = new ArrayBlockingQueue<Integer>(250);
    public static BlockingQueue<Integer> thirdFloorExitQueue = new ArrayBlockingQueue<Integer>(250);
    public static BlockingQueue<Integer> fourthFloorExitQueue = new ArrayBlockingQueue<Integer>(250);
    
    public void exit(){
        
        while(true){
            int randomExitPeopleCount = random.nextInt(5)+1;
            int randomExitFloor = random.nextInt(4)+1;
            System.out.println("AVM den çıkış yapacak kişi sayısı: "+randomExitPeopleCount+" Bulundukları kat: "+randomExitFloor);

            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(randomExitFloor == 1){
                
                try {
                    firstFloorExitQueue.put(randomExitPeopleCount);
                    //System.out.println("firstFloorExitQueue: "+firstFloorExitQueue.element()+" size: "+firstFloorExitQueue.size());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            else if(randomExitFloor == 2){
                
                try {
                    secondFloorExitQueue.put(randomExitPeopleCount);
                    //System.out.println("secondFloorExitQueue: "+secondFloorExitQueue.element()+" size: "+secondFloorExitQueue.size());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(randomExitFloor == 3){
                
                try {
                    thirdFloorExitQueue.put(randomExitPeopleCount);
                    //System.out.println("thirdFloorExitQueue: "+thirdFloorExitQueue.element()+ " size: "+thirdFloorExitQueue.size());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(randomExitFloor == 4){
                
                try {
                    fourthFloorExitQueue.put(randomExitPeopleCount);
                    //System.out.println("fourthFloorExitQueue: "+fourthFloorExitQueue.element()+" size: "+fourthFloorExitQueue.size());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
        
    }
    
    public static void totalPeopleInQueues(){
        
        Object[] objectsFirst = firstFloorExitQueue.toArray();
        Object[] objectsSecond = secondFloorExitQueue.toArray(); 
        Object[] objectsThird = firstFloorExitQueue.toArray(); 
        Object[] objectsFourth = firstFloorExitQueue.toArray(); 
        for(int i=0;i<destinationPeople.size();i++){
            totalPeople+= destinationPeople.get(i);
        }
        for (Object obj : objectsFirst){ 
            totalPeople+=(int)obj;
            //System.out.println("TOTAL WİTH OBJ"+total);
        }
        for (Object obj : objectsSecond){ 
            totalPeople+=(int)obj;
            //System.out.println("TOTAL WİTH OBJ"+total);
        }
        for (Object obj : objectsThird){ 
            totalPeople+=(int)obj;
            //System.out.println("TOTAL WİTH OBJ"+total);
        }
        for (Object obj : objectsFourth){ 
            totalPeople+=(int)obj;
            //System.out.println("TOTAL WİTH OBJ"+total);
        }
        
        
    }
    
    
    public void control(){ 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true){
        totalPeopleInQueues();
        int totalPeopleInAllQueues = totalPeople;    
        if(totalPeopleInAllQueues>limit*2){
            elevator2Working = true;
            System.out.println("TOPLAM BEKLEYEN KİŞİ SAYISI: "+totalPeopleInAllQueues);
            System.out.println("2. Asansör devrede...");
            lift2();

        }
        if(totalPeopleInAllQueues<limit*2){
            elevator2Working = false;
            //System.out.println("2. Asansör devre DIŞI...");
            

        }
        
   
        
        }
        
    }
    
    
    
    
    
    
    public  void lift(){
        destinationPeoplePrepare();
        /*
        destinationPeople.add(0);
        destinationPeople.add(0);
        destinationPeople.add(0);
        destinationPeople.add(0);
        destinationPeople.set(0, 0);
        destinationPeople.set(1, 0);
        destinationPeople.set(2, 0);
        destinationPeople.set(3, 0);
        */
        
        while(true){
            
            //System.out.println("ASANSOR 1 ÇALIŞIYORRRR.....................");
            
            if(direction == 1 && loginPeopleCount.size()>0 && loginFloor.size()>0){
                //try {
                    //Thread.sleep(1000);
                    //System.out.println("Yolculuk başlıyor...");
                //} catch (InterruptedException ex) {
                    //Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                //}
                int people = 0;
                while(people<= limit && loginPeopleCount.size()>0 && loginFloor.size()>0){
                    people += loginPeopleCount.element();//
                    destinationFloors.add(loginFloor.element());//
                    arrayListOptimizationPeople(loginPeopleCount.element(),loginFloor.element());//
                    try {
                        loginPeopleCount.take();//
                        loginFloor.take();//
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    arrayListOptimizationFloor(destinationFloors);
                
                while(currentFloorElevator1<4){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator1++;
                    System.out.println("Asansör "+currentFloorElevator1+". kata geldi...");
                    if(destinationFloors.contains(currentFloorElevator1)){
                        System.out.println(currentFloorElevator1+". Katta "+destinationPeople.get(currentFloorElevator1-1) +" yolcular indirildi...(1.ASANSÖR)");
                        destinationFloors.remove(0);
                        destinationPeople.set(currentFloorElevator1-1, 0);
                        
                        }
                    
                    }
                direction = 0;
                    
                if(direction==0){
                    /*
                    System.out.println("DestinationPeople 0:"+destinationPeople.get(0));
                    System.out.println("DestinationPeople 1:"+destinationPeople.get(1));
                    System.out.println("DestinationPeople 2:"+destinationPeople.get(2));
                    System.out.println("DestinationPeople 3:"+destinationPeople.get(3));
                    System.out.println("goingFirstFloorPeople: "+goingFirstFloorPeople);
                    System.out.println("goingSecondFloorPeople: "+goingSecondFloorPeople);
                    System.out.println("goingThirdFloorPeople: "+goingThirdFloorPeople);
                    System.out.println("goingFourthFloorPeople: "+goingFourthFloorPeople);
                    */
                    break;
                }
                
                
                }
                
            }
            else if(direction==0 && (fourthFloorExitQueue.size()>0 || thirdFloorExitQueue.size()>0 || secondFloorExitQueue.size()>0 || firstFloorExitQueue.size()>0  )){
                //System.out.println("destinationPeople: "+destinationPeople.size()+"destinationFloors: "+destinationFloors.size());
                int people = 0;
                
                if(fourthFloorExitQueue.size()>0){
                    Integer x = fourthFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //fourthFloorExitQueue.remove();
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator1 = 0;
                    exitPeopleCount+=people;
                    System.out.println("1.Asansor 0.kata geldi...");
                    System.out.println(people+" kişi AVM'den çıkış yaptı...(1.ASANSÖR)");
                    direction =1;
                    
                }else if(thirdFloorExitQueue.size()>0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("1.Asansor 3. Kata geldi...");
                    Integer x = thirdFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //thirdFloorExitQueue.remove();
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator1 = 0;
                    exitPeopleCount+=people;
                    System.out.println("1.Asansor 0.kata geldi...");
                    System.out.println(people+" kişi AVM'den çıkış yaptı...(1.ASANSÖR)");
                    direction =1;
                }
                else if(secondFloorExitQueue.size()>0){
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("1.Asansor 2. Kata geldi...");
                    Integer x = secondFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //secondFloorExitQueue.remove();
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator1 = 0;
                    exitPeopleCount+=people;
                    System.out.println("1.Asansor 0.kata geldi...");
                    System.out.println(people+" kişi AVM'den çıkış yaptı...(1.ASANSÖR)");
                    direction =1;
                }
                else if(firstFloorExitQueue.size()>0){
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("1.Asansor 1. Kata geldi...");
                    Integer x = firstFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //firstFloorExitQueue.remove();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator1 = 0;
                    exitPeopleCount+=people;
                    System.out.println("1.Asansor 0.kata geldi...");
                    System.out.println(people+" kişi AVM'den çıkış yaptı...(1.ASANSÖR)");
                    direction =1;
                }
                System.out.println("TOPLAMDA ÇIKIŞ YAPAN KİŞİ SAYISI: "+exitPeopleCount+"(1.ASANSÖR)");
                
            }
        
        
        
       }
    
    }
    public void lift2(){
        //destinationPeoplePrepare();
        try {
            /*
            destinationPeople.add(0);
            destinationPeople.add(0);
            destinationPeople.add(0);
            destinationPeople.add(0);
            destinationPeople.set(0, 0);
            destinationPeople.set(1, 0);
            destinationPeople.set(2, 0);
            destinationPeople.set(3, 0);
            */
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(elevator2Working){
            //System.out.println("ASANSOR 2 ÇALIŞIYORRRR.......");
            if(direction == 1 && loginPeopleCount.size()>0 && loginFloor.size()>0){
                //try {
                    //Thread.sleep(1000);
                    //System.out.println("Yolculuk başlıyor...");
                //} catch (InterruptedException ex) {
                    //Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                //}
                int people = 0;
                while(people<= limit && loginPeopleCount.size()>0 && loginFloor.size()>0){
                    people += loginPeopleCount.element();//
                    destinationFloors.add(loginFloor.element());//
                    arrayListOptimizationPeople(loginPeopleCount.element(),loginFloor.element());//
                    try {
                        loginPeopleCount.take();//
                        loginFloor.take();//
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    arrayListOptimizationFloor(destinationFloors);
                
                while(currentFloorElevator2<4){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator2++;
                    System.out.println("2.Asansör "+currentFloorElevator2+". kata geldi...");
                    if(destinationFloors.contains(currentFloorElevator2)){
                        System.out.println(currentFloorElevator2+". Katta "+destinationPeople.get(currentFloorElevator2-1) +" yolcular indirildi...(2.ASANSÖR)");
                        destinationFloors.remove(0);
                        destinationPeople.set(currentFloorElevator2-1, 0);
                        
                        }
                    
                    }
                direction = 0;
                    
                if(direction==0){
                    /*
                    System.out.println("DestinationPeople 0:"+destinationPeople.get(0));
                    System.out.println("DestinationPeople 1:"+destinationPeople.get(1));
                    System.out.println("DestinationPeople 2:"+destinationPeople.get(2));
                    System.out.println("DestinationPeople 3:"+destinationPeople.get(3));
                    System.out.println("goingFirstFloorPeople: "+goingFirstFloorPeople);
                    System.out.println("goingSecondFloorPeople: "+goingSecondFloorPeople);
                    System.out.println("goingThirdFloorPeople: "+goingThirdFloorPeople);
                    System.out.println("goingFourthFloorPeople: "+goingFourthFloorPeople);
                    */
                    break;
                }
                
                
                }
                
            }
            else if(direction==0 && (fourthFloorExitQueue.size()>0 || thirdFloorExitQueue.size()>0 || secondFloorExitQueue.size()>0 || firstFloorExitQueue.size()>0  )){
                try {
                    //System.out.println("destinationPeople: "+destinationPeople.size()+"destinationFloors: "+destinationFloors.size());
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                }
                int people = 0;
                if(fourthFloorExitQueue.size()>0){
                    
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Integer x = fourthFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //fourthFloorExitQueue.remove();
                    
                    currentFloorElevator2 = 0;
                    exitPeopleCount+=people;
                    System.out.println("2.Asansor 0.kata geldi...");
                    System.out.println(people+" kişi AVM'den çıkış yaptı...(2.ASANSÖR)");
                    direction =1;
                    
                }else if(thirdFloorExitQueue.size()>0){
                    
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("2.Asansor 3. Kata geldi...");
                    
                    //people = thirdFloorExitQueue.poll();
                    Integer x = thirdFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //thirdFloorExitQueue.remove();
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator2 = 0;
                    exitPeopleCount+=people;
                    System.out.println("2.Asansor 0.kata geldi...");
                    System.out.println(people+" kişi AVM'den çıkış yaptı...(2.ASANSÖR)");
                    direction =1;
                }
                else if(secondFloorExitQueue.size()>0){
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("2.Asansor 2. Kata geldi...");
                    Integer x = secondFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //secondFloorExitQueue.remove();
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator2 = 0;
                    exitPeopleCount+=people;
                    System.out.println("2.Asansor 0.kata geldi..");
                    System.out.println(people+" kişi AVM'den çıkış yaptı...(2.ASANSÖR)");
                    direction =1;
                }
                else if(firstFloorExitQueue.size()>0){
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("2.Asansor 1. Kata geldi...");
                    Integer x = firstFloorExitQueue.poll();
                    if(x != null){
                        people = x;
                    }
                    //firstFloorExitQueue.remove();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentFloorElevator2 = 0;
                    exitPeopleCount+=people;
                    
                    direction =1;
                }
                System.out.println("TOPLAMDA ÇIKIŞ YAPAN KİŞİ SAYISI: "+exitPeopleCount+" (2.ASANSÖR)");
                
            }
        
        
        
       }
    
    }
    
    
    
    
    
    
}
