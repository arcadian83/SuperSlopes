package net.arcadian83.common;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class ExtraMetadata extends TileEntity {
	
	public int meta = 0;

	//public ExtraMetadata(){}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		meta = nbt.getInteger("meta");
		//System.out.println("NBT READ: " + meta);
		//int test = meta;
		//blockMetadata = meta;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		//meta = blockMetadata;
		nbt.setInteger("meta", meta);
		//System.out.println("NBT WRITE: " + meta);
		
	}
	
	public void setMeta(int meta) {
		//World world = getWorldObj();
		//world.setBlockMetadata(xCoord, yCoord, zCoord, meta);
		this.meta = meta;
		
		
	}
	
	public int getMeta() {
		return meta;
	}

	/*public void processActivate(EntityPlayer par5EntityPlayer, World world) {
		if (!visitor1.equals(par5EntityPlayer.getEntityName())) {
			visitor5 = visitor4;
			visitor4 = visitor3;
			visitor3 = visitor2;
			visitor2 = visitor1;
			visitor1 = par5EntityPlayer.getEntityName();
		}
		// System.out.println("Visitors: " + visitor1 + ", " + visitor2 + ", " +
		// visitor3 + ", " + visitor4 + ", " + visitor5);
		par5EntityPlayer.addChatMessage("Visitors: " + visitor1 + ", "
				+ visitor2 + ", " + visitor3 + ", " + visitor4 + ", "
				+ visitor5);
		world.notifyBlockChange(xCoord, yCoord, zCoord, 2);
	}*/
	
	//public void updateEntity() {
		//worldObj.setBlockMetadata(xCoord, yCoord, zCoord, meta);
		//System.out.println("UPDATE ENTITY  (" + xCoord + "," + yCoord + "," + zCoord + ") meta: " + meta);
	//}
	
	
}
