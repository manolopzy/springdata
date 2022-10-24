package com.worldexplorer.springbootdatamongodb.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author tanku
 *
 */
public class Skill {
	
	private int id;
	
	private int skillId;
	
	private int playerId;
	
	private int level;
	
	
    private void writeObject(ObjectOutputStream oos) 
      throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(id);
        oos.writeInt(skillId);
        oos.writeInt(playerId);
        oos.writeInt(level);
    }

    private void readObject(ObjectInputStream ois) 
      throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        this.id = ois.readInt();
        this.skillId = ois.readInt();
        this.playerId = ois.readInt();
        this.level = ois.readInt();
    }
}
