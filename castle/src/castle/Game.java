package castle;

import castle.handle.Handler;
import castle.handle.HandlerBye;
import castle.handle.HandlerGo;
import castle.handle.HandlerHelp;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handlers = new HashMap<String, Handler>();

        
    public Game() 
    {
        handlers.put("go", new HandlerGo(this));
        handlers.put("bye", new HandlerBye());
        handlers.put("help",new HandlerHelp());
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        
        //	初始化房间的出口
//        outside.setExits(null, lobby, study, pub);
//        lobby.setExits(null, null, null, outside);
//        pub.setExits(null, outside, null, null);
//        study.setExits(outside, bedroom, null, null);
//        bedroom.setExits(null, null, null, study);

        outside.setExit("east",lobby);
        outside.setExit("south",study);
        outside.setExit("west",pub);
        lobby.setExit("west",outside);
        pub.setExit("east",outside);
        study.setExit("north",outside);
        study.setExit("east",bedroom);
        bedroom.setExit("west",study);

        currentRoom = outside;  //	从城堡门外开始
    }

    private void nowRoom(){
        System.out.println("现在你在" + currentRoom);
        System.out.print("出口有：");
//        将出口信息封装在Room类中，降低程序之间的耦合
//        if(currentRoom.northExit != null)
//            System.out.print("north ") ;
//        if(currentRoom.eastExit != null)
//            System.out.print("east ");
//        if(currentRoom.southExit != null)
//            System.out.print("south ");
//        if(currentRoom.westExit != null)
//            System.out.print("west ");
        System.out.println(currentRoom.getExitDesc());
        System.out.println();
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        nowRoom();

    }

    // 以下为用户命令

//    private void printHelp()
//    {
//        System.out.print("迷路了吗？你可以做的命令有：go bye help");
//        System.out.println("如：\tgo east");
//    }

    public void goRoom(String direction)
    {
//        将处理逻辑转移到 Room 类中
        Room nextRoom = currentRoom.getExit(direction);
//        Room nextRoom = null;
//        if(direction.equals("north")) {
//            nextRoom = currentRoom.northExit;
//        }
//        if(direction.equals("east")) {
//            nextRoom = currentRoom.eastExit;
//        }
//        if(direction.equals("south")) {
//            nextRoom = currentRoom.southExit;
//        }
//        if(direction.equals("west")) {
//            nextRoom = currentRoom.westExit;
//        }

        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            nowRoom();
        }
    }

    public void play(){
        Scanner in = new Scanner(System.in);

        while ( true ) {
        		String line = in.nextLine();
        		String[] words = line.split(" ");
//                利用Hash表<K, V>的特性，如果用户输入"bye"，通过handler.get()得出handler
//                类型下面这句就相当于：Handler handler = new HandlerBye();
        		Handler handler = handlers.get(words[0]);
        		String value = " ";
        		if (words.length > 1){
        		    value = words[1];
                }
        		if (handler != null){
//                    此时handler为HandlerBye型，没有value值
        		    handler.doCmd(value);
//                    HandlerBye继承了Handler中的isBye()方法并将其覆盖，此时handler.isBye()返回true
        		    if (handler.isBye()){
        		        break;
                    }
                }
//        		if ( words[0].equals("help") ) {
//        			game.printHelp();
//        		} else if (words[0].equals("go") ) {
//        			game.goRoom(words[1]);
//        		} else if ( words[0].equals("bye") ) {
//        			break;
//        		}
        }
        in.close();
    }


	public static void main(String[] args) {
		Game game = new Game();
		game.printWelcome();

//        while ( true ) {
//        		String line = in.nextLine();
//        		String[] words = line.split(" ");
//        		if ( words[0].equals("help") ) {
//        			game.printHelp();
//        		} else if (words[0].equals("go") ) {
//        			game.goRoom(words[1]);
//        		} else if ( words[0].equals("bye") ) {
//        			break;
//        		}
//        }
        game.play();
        System.out.println("感谢您的光临。再见！");
	}

}
