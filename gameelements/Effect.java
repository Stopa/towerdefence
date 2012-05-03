package towerdefence.gameelements;

//Effectid töötavad umbes nii:
//iga turni lõpus käiakse üle kõik effektid ja kutsutakse välja .process() meetod igaühe peal
//kui see turn ei juhtu midagi, siis lihtsalt suurendatakse elapsedTime väärtust
//kui juhtub midagi, siis lisaks tehakse see vastav aktsioon

//seejärel käiakse UUESTI üle kõik effektid, ja kui selle aeg on läbi, eemaldatakse see
//effektide järjendist. 

public class Effect {
    
    private final Target targetType; 
    private final Type type; 
    
    private final int totalTime;
    private final int amount; //damage, slow amount, jne.. olenevalt tüübist
    private int elapsedTime;         
    
    
    public Effect(int totalTime, int amount, Target targetType, Type type) {
        this.totalTime = totalTime;
        this.elapsedTime = 0; 
        this.amount = amount; 
        this.targetType = targetType;
        this.type = type; 
    }
    
    public void process() {
        //TODO - kogu effecti protsessimine tehakse siin, olenevalt tüübist!!!
        
        increaseElapsedTime(); 
    }
    
    //TODO - kui tõsi, siis iga turni lõpus eemaldatakse
    public boolean isElapsed() {
        return this.elapsedTime >= this.totalTime; 
    }
    
    public int getTotalTime() {
        return this.totalTime; 
    }
    
    public Target getTargetType() {
        return this.targetType; 
    }
    
    public Type getType() {
        return this.type; 
    }
    
    public int getAmount() {
        return this.amount; 
    }
    
    private void increaseElapsedTime() {
        this.elapsedTime++; 
        if (elapsedTime > totalTime) throw new AssertionError(); //TODO
    }
            
    public enum Target {
        Single, Splash; 
    }
    
    public enum Type {
        Damage, Slow, DecreaseArmor; 
    }

}
