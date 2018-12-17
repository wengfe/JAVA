package castle;

import java.util.HashMap;

public class Room {
//    使用容器代替 硬编码的方向变量
    private String description;
//    private Room northExit;
//    private Room southExit;
//    private Room eastExit;
//    private Room westExit;
    private HashMap<String,Room>  exits = new HashMap<String,Room>();


    public Room(String description)
    {
        this.description = description;
    }

//    录入房间空间位置的方式改变，由原来的对一个房间的四个方向分别定义，改为对一个房间
//    自定义方向以及该方向上的新房间
    public void setExit(String dir, Room room){
        exits.put(dir,room);
    }

//    public void setExits(Room north, Room east, Room south, Room west)
//    {
//        if(north != null)
//            northExit = north;
//        if(east != null)
//            eastExit = east;
//        if(south != null)
//            southExit = south;
//        if(west != null)
//            westExit = west;
//    }

    public Room getExit(String direction){
//        Room ret = null;
//        if(direction.equals("north")) {
//            ret = northExit;
//        }
//        if(direction.equals("east")) {
//            ret = eastExit;
//        }
//        if(direction.equals("south")) {
//            ret = southExit;
//        }
//        if(direction.equals("west")) {
//            ret = westExit;
//        }
        return exits.get(direction);
    }

    public String getExitDesc(){
//        使用 StringBuffer 是因为 String 对象是管理者，每次对 String 对象的修改都是新增一个 String 对象，
//        对系统开销比较大，但是 StringBuffer支持修改
        StringBuffer sb = new StringBuffer();
        for (String dir :
                exits.keySet()) {
            sb.append(dir+" ");
        }
//        if (northExit != null)
//            sb.append("north ");
//        if (eastExit != null)
//            sb.append("east ");
//        if (westExit != null)
//            sb.append("west ");
//        if (southExit != null)
//            sb.append("south ");
        return sb.toString();
    }



    @Override
    public String toString()
    {
        return description;
    }
}
