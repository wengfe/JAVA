import java.util.ArrayList;

public class NoteMain {
    private ArrayList<String> notes = new ArrayList<String>();
    public void  add(String s){
        notes.add(s);
    }

    public void remove(int index){
        notes.remove(index);
    }

    public String getNote(int index){
        return notes.get(index);


//        ArrayList 中的 contains 方法， notes.contains(object) == notes.equals(object) 方法
//        if(notes.contains(notes.get(index)))
//        {
//            return notes.get(index);
//        }
//        else {
//            return "NOT FUND";
//        }
    }

    public int getSize(){
        return notes.size();
    }

    public String[] getList(){
        String[] a = new String[notes.size()];
        notes.toArray(a);
        return a;
    }
    public static void main(String[] args) {
        NoteMain note = new NoteMain();
        note.add("first");
        note.add("second");
        note.add("second");
        note.remove(2);
        System.out.println(note.getSize());
        System.out.println(note.getNote(1));
        System.out.println(note.getNote(0));
        System.out.println(note.getNote(2));
        System.out.println(note.getList());

        for (String s : note.getList())
        {
            System.out.println(s);

        }
    }
}
