package main;

import java.util.ArrayList;

public class Database {
//    private ArrayList<CD> listCD = new ArrayList<CD>();
//    private ArrayList<DVD> listDVD = new ArrayList<DVD>();
    private ArrayList<Item> listItem = new ArrayList<Item>();

//    public void add(CD cd){
//        listCD.add(cd);
//    }
//
//    public void add(DVD dvd){
//        listDVD.add(dvd);
//    }
    public void add(Item item){
        listItem.add(item);
    }

    public void list(){
//        for (CD cd :
//                listCD) {
//            cd.print();
//        }
//        for (DVD dvd :
//                listDVD) {
//            dvd.print();
//        }
        for (Item item :
                listItem) {
            item.print();
        }
    }

    public static void main(String[] args) {
        Database db = new Database();
        db.add(new CD("那些你很冒险的梦","林俊杰",4,6,"新专辑"));
        db.add(new CD("飞云之上","林俊杰&韩红",3,8,"节目歌曲"));
        db.add(new DVD("海王","陆XX",160,"DC宇宙"));
        db.add(new DVD("蜘蛛侠平行宇宙","XX",180,"漫威宇宙"));
        db.add(new VideoGame("超凡蜘蛛侠",280,false,"PS4",1));
        db.list();
    }
}
