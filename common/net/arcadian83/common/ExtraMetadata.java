package net.arcadian83.common;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class ExtraMetadata extends TileEntity {
	int meta = 0;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.meta = nbt.getInteger("meta");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("meta", meta);
	}
	
	public void setMeta(int meta) {
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
}
