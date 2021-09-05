import game.*;
import java.io.*;

// ���� ��û...

public class practice4_Main {
	public static final String SAVEFILENAME = "game.dat";
	public static void main(String[] args) {
		Gamer gamer = new Gamer(100);
		Memento memento = loadMemento();
		
		if (memento != null) {
			System.out.println("������ ������ ������� �����մϴ�.");
			gamer.restoreMemento(memento);
		}
		else {
			System.out.println("���� �����մϴ�.");
			memento = gamer.createMemento();
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println("==== " + i);
			System.out.println("���� : " + gamer);
			
			gamer.bet();
			
			System.out.println("�������� " + gamer.getMoney() + "���� �Ǿ����ϴ�.");
			
			if (gamer.getMoney() > memento.getMoney()) {
				System.out.println("  (���� ���������Ƿ� ������ ���¸� ��������)");
				memento = gamer.createMemento();
				saveMemento(memento);
			}
			else if (gamer.getMoney() < memento.getMoney() / 2) {
				System.out.println("  (���� ���������Ƿ� ������ ���·� ��������)");
				gamer.restoreMemento(memento);
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			System.out.println();
		}
	}
	public static void saveMemento(Memento memento) {
		try {
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream(SAVEFILENAME));
			out.writeObject(memento);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Memento loadMemento() {
		Memento memento = null;
		try {
			ObjectInput in = new ObjectInputStream(new FileInputStream(SAVEFILENAME));
			memento = (Memento)in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return memento;
	}
}