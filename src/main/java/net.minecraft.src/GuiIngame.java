//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minecraft.src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiIngame extends Gui {
	private static RenderItem itemRenderer = new RenderItem();
	private List chatMessageList = new ArrayList();
	private Random rand = new Random();
	private Minecraft mc;
	public String field_933_a = null;
	private int updateCounter = 0;
	private String recordPlaying = "";
	private int recordPlayingUpFor = 0;
	private boolean field_22065_l = false;
	public float damageGuiPartialTime;
	float prevVignetteBrightness = 1.0F;

	public GuiIngame(Minecraft minecraft) {
		this.mc = minecraft;
	}

	private boolean slimeChunk(Minecraft minecraft, int chunkX, int chunkZ) {
		Chunk chunk = minecraft.theWorld.getChunkFromChunkCoords(chunkX, chunkZ);
		return chunk.func_997_a(987234911L).nextInt(10) == 0;
	}

	public void renderGameOverlay(float f, boolean bl, int i, int j) {
		ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		FontRenderer var8 = this.mc.fontRenderer;
		this.mc.entityRenderer.func_905_b();
		GL11.glEnable(3042);
		if (Minecraft.isFancyGraphicsEnabled()) {
			this.renderVignette(this.mc.thePlayer.getEntityBrightness(f), var6, var7);
		}

		ItemStack var9 = this.mc.thePlayer.inventory.armorItemInSlot(3);
		if (!this.mc.gameSettings.thirdPersonView && var9 != null && var9.itemID == Block.pumpkin.blockID) {
			this.renderPumpkinBlur(var6, var7);
		}

		float var10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * f;
		if (var10 > 0.0F) {
			this.renderPortalOverlay(var10, var6, var7);
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/gui/gui.png"));
		InventoryPlayer var11 = this.mc.thePlayer.inventory;
		this.zLevel = -90.0F;
		this.drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
		this.drawTexturedModalRect(var6 / 2 - 91 - 1 + var11.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/gui/icons.png"));
		GL11.glEnable(3042);
		GL11.glBlendFunc(775, 769);
		this.drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
		GL11.glDisable(3042);
		boolean var12 = this.mc.thePlayer.heartsLife / 3 % 2 == 1;
		if (this.mc.thePlayer.heartsLife < 10) {
			var12 = false;
		}

		int var13 = this.mc.thePlayer.health;
		int var14 = this.mc.thePlayer.prevHealth;
		this.rand.setSeed((long)(this.updateCounter * 312871));
		if (this.mc.playerController.shouldDrawHUD()) {
			int var15 = this.mc.thePlayer.getPlayerArmorValue();

			for(int var16 = 0; var16 < 10; ++var16) {
				int var17 = var7 - 32;
				if (var15 > 0) {
					int var18 = var6 / 2 + 91 - var16 * 8 - 9;
					if (var16 * 2 + 1 < var15) {
						this.drawTexturedModalRect(var18, var17, 34, 9, 9, 9);
					}

					if (var16 * 2 + 1 == var15) {
						this.drawTexturedModalRect(var18, var17, 25, 9, 9, 9);
					}

					if (var16 * 2 + 1 > var15) {
						this.drawTexturedModalRect(var18, var17, 16, 9, 9, 9);
					}
				}

				byte var40 = 0;
				if (var12) {
					var40 = 1;
				}

				int var19 = var6 / 2 - 91 + var16 * 8;
				if (var13 <= 4) {
					var17 += this.rand.nextInt(2);
				}

				this.drawTexturedModalRect(var19, var17, 16 + var40 * 9, 0, 9, 9);
				if (var12) {
					if (var16 * 2 + 1 < var14) {
						this.drawTexturedModalRect(var19, var17, 70, 0, 9, 9);
					}

					if (var16 * 2 + 1 == var14) {
						this.drawTexturedModalRect(var19, var17, 79, 0, 9, 9);
					}
				}

				if (var16 * 2 + 1 < var13) {
					this.drawTexturedModalRect(var19, var17, 52, 0, 9, 9);
				}

				if (var16 * 2 + 1 == var13) {
					this.drawTexturedModalRect(var19, var17, 61, 0, 9, 9);
				}
			}

			if (this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
				int var29 = (int)Math.ceil((double)(this.mc.thePlayer.air - 2) * (double)10.0F / (double)300.0F);
				int var34 = (int)Math.ceil((double)this.mc.thePlayer.air * (double)10.0F / (double)300.0F) - var29;

				for(int var41 = 0; var41 < var29 + var34; ++var41) {
					if (var41 < var29) {
						this.drawTexturedModalRect(var6 / 2 - 91 + var41 * 8, var7 - 32 - 9, 16, 18, 9, 9);
					} else {
						this.drawTexturedModalRect(var6 / 2 - 91 + var41 * 8, var7 - 32 - 9, 25, 18, 9, 9);
					}
				}
			}
		}

		GL11.glDisable(3042);
		GL11.glEnable(32826);
		GL11.glPushMatrix();
		GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();

		for(int var24 = 0; var24 < 9; ++var24) {
			int var30 = var6 / 2 - 90 + var24 * 20 + 2;
			int var35 = var7 - 16 - 3;
			this.renderInventorySlot(var24, var30, var35, f);
		}

		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(32826);
		if (this.mc.thePlayer.func_22060_M() > 0) {
			GL11.glDisable(2929);
			GL11.glDisable(3008);
			int var25 = this.mc.thePlayer.func_22060_M();
			float var31 = (float)var25 / 100.0F;
			if (var31 > 1.0F) {
				var31 = 1.0F - (float)(var25 - 100) / 10.0F;
			}

			int var36 = (int)(220.0F * var31) << 24 | 1052704;
			this.drawRect(0, 0, var6, var7, var36);
			GL11.glEnable(3008);
			GL11.glEnable(2929);
		}

		if (this.mc.gameSettings.showDebugInfo) {
			ScaledResolution scaledResolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			GL11.glPushMatrix();
			if (Minecraft.hasPaidCheckTime > 0L) {
				GL11.glTranslatef(0.0F, 32.0F, 0.0F);
			}

			double roundedPosX = Math.round(this.mc.thePlayer.posX * 1000.0) / 1000.0;
			double roundedPosY = Math.round(this.mc.thePlayer.posY * 1000.0) / 1000.0;
			double roundedPosZ = Math.round(this.mc.thePlayer.posZ * 1000.0) / 1000.0;

			int playerPosX = MathHelper.floor_double(this.mc.thePlayer.posX);
			int playerPosY = MathHelper.floor_double(this.mc.thePlayer.posY);
			int playerPosZ = MathHelper.floor_double(this.mc.thePlayer.posZ);
			int playerFacingInt = MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5) & 3;
			String playerFacing;
			switch (playerFacingInt) {
				case 0:
					playerFacing = "South (0)";
					break;
				case 1:
					playerFacing = "West (1)";
					break;
				case 2:
					playerFacing = "North (2)";
					break;
				case 3:
					playerFacing = "East (3)";
					break;
			}

			int chunkPositionX = playerPosX >> 4;
			int chunkPositionZ = playerPosZ >> 4;
			long day = this.mc.theWorld.getWorldTime() / 24000L;
			float timeHours = (this.mc.theWorld.getCelestialAngle(1.0F) * 24.0F + 12.0F) % 24.0F;
			int hours = MathHelper.floor_float(timeHours);
			int minutes = MathHelper.floor_float(timeHours * 60.0F) - hours * 60;
			int currentDifficulty = this.mc.theWorld.difficultySetting;
			String difficulty;
			if (this.mc.theWorld.multiplayerWorld) {
				difficulty = "MpServer";
			} else {
				switch (currentDifficulty) {
					case 0:
						difficulty = "Peaceful (0)";
						break;
					case 1:
						difficulty = "Easy (1)";
						break;
					case 2:
						difficulty = "Normal (2)";
						break;
					case 3:
						difficulty = "Hard (3)";
						break;
				}
			}

			String biomeName = this.mc.theWorld.getWorldChunkManager().getBiomeGenAt(playerPosX, playerPosZ).biomeName;
			int lightLevel = this.mc.thePlayer.worldObj.getBlockLightValue(playerPosX, (int) playerPosY, playerPosZ);
			long seed = this.mc.theWorld.getRandomSeed();
			String worldName = this.mc.theWorld.getWorldInfo().getWorldName();
			var8.drawStringWithShadow("Minecraft Beta 1.7.3 (" + this.mc.debug + ")", 2, 2, 16777215);
			var8.drawStringWithShadow(this.mc.func_6241_m(), 2, 12, 16777215);
			var8.drawStringWithShadow(this.mc.func_6262_n(), 2, 22, 16777215);
			var8.drawStringWithShadow(this.mc.func_6245_o(), 2, 32, 16777215);
			var8.drawStringWithShadow(this.mc.func_21002_o(), 2, 42, 16777215);
			long var26 = Runtime.getRuntime().maxMemory();
			long var37 = Runtime.getRuntime().totalMemory();
			long var46 = Runtime.getRuntime().freeMemory();
			long var21 = var37 - var46;
			String var23 = "Used memory: " + var21 * 100L / var26 + "% (" + var21 / 1024L / 1024L + "MB) of " + var26 / 1024L / 1024L + "MB";
			this.drawString(var8, var23, var6 - var8.getStringWidth(var23) - 2, 2, 14737632);
			var23 = "Allocated memory: " + var37 * 100L / var26 + "% (" + var37 / 1024L / 1024L + "MB)";
			this.drawString(var8, var23, var6 - var8.getStringWidth(var23) - 2, 12, 14737632);
			this.drawString(var8, "XYZ: " + roundedPosX + " / " + roundedPosY + " / " + roundedPosZ, 2, 67, 14737632);
			this.drawString(var8, "Chunk XZ: " + chunkPositionX + " / " + chunkPositionZ, 2, 76, 14737632);
			this.drawString(var8, "Facing: " + playerFacing, 2, 85, 14737632);
			this.drawString(var8, "Biome: " + biomeName, 2, 94, 14737632);
			this.drawString(var8, "Light Level: " + lightLevel, 2, 103, 14737632);
			this.drawString(var8, "Time: " + String.format("%02d:%02d", hours, minutes), 2, 112, 14737632);
			this.drawString(var8, "Day: " + day, 2, 121, 14737632);
			this.drawString(var8, "Slime: " + this.slimeChunk(this.mc, chunkPositionX, chunkPositionZ), 2, 130, 14737632);
			this.drawString(
					var8, "World Name: " + worldName, scaledResolution.getScaledWidth() - this.mc.fontRenderer.getStringWidth("World Name: " + worldName) + -2, 42, 14737632
			);
			this.drawString(
					var8,
					"World Difficulty: " + difficulty,
					scaledResolution.getScaledWidth() - this.mc.fontRenderer.getStringWidth("World Difficulty: " + difficulty) + -2,
					51,
					14737632
			);
			this.drawString(var8, "World Seed: " + seed, scaledResolution.getScaledWidth() - this.mc.fontRenderer.getStringWidth("World Seed: " + seed) + -2, 60, 14737632);
			GL11.glPopMatrix();
		}

		if (this.recordPlayingUpFor > 0) {
			float var27 = (float)this.recordPlayingUpFor - f;
			int var32 = (int)(var27 * 256.0F / 20.0F);
			if (var32 > 255) {
				var32 = 255;
			}

			if (var32 > 0) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 48), 0.0F);
				GL11.glEnable(3042);
				GL11.glBlendFunc(770, 771);
				int var38 = 16777215;
				if (this.field_22065_l) {
					var38 = Color.HSBtoRGB(var27 / 50.0F, 0.7F, 0.6F) & 16777215;
				}

				var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var38 + (var32 << 24));
				GL11.glDisable(3042);
				GL11.glPopMatrix();
			}
		}

		byte var28 = 10;
		boolean var33 = false;
		if (this.mc.currentScreen instanceof GuiChat) {
			var28 = 20;
			var33 = true;
		}

		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3008);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, (float)(var7 - 48), 0.0F);

		for(int var39 = 0; var39 < this.chatMessageList.size() && var39 < var28; ++var39) {
			if (((ChatLine)this.chatMessageList.get(var39)).updateCounter < 200 || var33) {
				double var42 = (double)((ChatLine)this.chatMessageList.get(var39)).updateCounter / (double)200.0F;
				var42 = (double)1.0F - var42;
				var42 *= (double)10.0F;
				if (var42 < (double)0.0F) {
					var42 = (double)0.0F;
				}

				if (var42 > (double)1.0F) {
					var42 = (double)1.0F;
				}

				var42 *= var42;
				int var20 = (int)((double)255.0F * var42);
				if (var33) {
					var20 = 255;
				}

				if (var20 > 0) {
					byte var47 = 2;
					int var22 = -var39 * 9;
					String var49 = ((ChatLine)this.chatMessageList.get(var39)).message;
					this.drawRect(var47, var22 - 1, var47 + 320, var22 + 8, var20 / 2 << 24);
					GL11.glEnable(3042);
					var8.drawStringWithShadow(var49, var47, var22, 16777215 + (var20 << 24));
				}
			}
		}

		GL11.glPopMatrix();
		GL11.glEnable(3008);
		GL11.glDisable(3042);
	}

	private void renderPumpkinBlur(int i, int j) {
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3008);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
		Tessellator var3 = Tessellator.instance;
		var3.startDrawingQuads();
		var3.addVertexWithUV((double)0.0F, (double)j, (double)-90.0F, (double)0.0F, (double)1.0F);
		var3.addVertexWithUV((double)i, (double)j, (double)-90.0F, (double)1.0F, (double)1.0F);
		var3.addVertexWithUV((double)i, (double)0.0F, (double)-90.0F, (double)1.0F, (double)0.0F);
		var3.addVertexWithUV((double)0.0F, (double)0.0F, (double)-90.0F, (double)0.0F, (double)0.0F);
		var3.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderVignette(float f, int i, int j) {
		f = 1.0F - f;
		if (f < 0.0F) {
			f = 0.0F;
		}

		if (f > 1.0F) {
			f = 1.0F;
		}

		this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(f - this.prevVignetteBrightness) * 0.01);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(0, 769);
		GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
		Tessellator var4 = Tessellator.instance;
		var4.startDrawingQuads();
		var4.addVertexWithUV((double)0.0F, (double)j, (double)-90.0F, (double)0.0F, (double)1.0F);
		var4.addVertexWithUV((double)i, (double)j, (double)-90.0F, (double)1.0F, (double)1.0F);
		var4.addVertexWithUV((double)i, (double)0.0F, (double)-90.0F, (double)1.0F, (double)0.0F);
		var4.addVertexWithUV((double)0.0F, (double)0.0F, (double)-90.0F, (double)0.0F, (double)0.0F);
		var4.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBlendFunc(770, 771);
	}

	private void renderPortalOverlay(float f, int i, int j) {
		if (f < 1.0F) {
			f *= f;
			f *= f;
			f = f * 0.8F + 0.2F;
		}

		GL11.glDisable(3008);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/terrain.png"));
		float var4 = (float)(Block.portal.blockIndexInTexture % 16) / 16.0F;
		float var5 = (float)(Block.portal.blockIndexInTexture / 16) / 16.0F;
		float var6 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16.0F;
		float var7 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16.0F;
		Tessellator var8 = Tessellator.instance;
		var8.startDrawingQuads();
		var8.addVertexWithUV((double)0.0F, (double)j, (double)-90.0F, (double)var4, (double)var7);
		var8.addVertexWithUV((double)i, (double)j, (double)-90.0F, (double)var6, (double)var7);
		var8.addVertexWithUV((double)i, (double)0.0F, (double)-90.0F, (double)var6, (double)var5);
		var8.addVertexWithUV((double)0.0F, (double)0.0F, (double)-90.0F, (double)var4, (double)var5);
		var8.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderInventorySlot(int i, int j, int k, float f) {
		ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[i];
		if (var5 != null) {
			float var6 = (float)var5.animationsToGo - f;
			if (var6 > 0.0F) {
				GL11.glPushMatrix();
				float var7 = 1.0F + var6 / 5.0F;
				GL11.glTranslatef((float)(j + 8), (float)(k + 12), 0.0F);
				GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
				GL11.glTranslatef((float)(-(j + 8)), (float)(-(k + 12)), 0.0F);
			}

			itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, j, k);
			if (var6 > 0.0F) {
				GL11.glPopMatrix();
			}

			itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, j, k);
		}
	}

	public void updateTick() {
		if (this.recordPlayingUpFor > 0) {
			--this.recordPlayingUpFor;
		}

		++this.updateCounter;

		for(int var1 = 0; var1 < this.chatMessageList.size(); ++var1) {
			++((ChatLine)this.chatMessageList.get(var1)).updateCounter;
		}

	}

	public void clearChatMessages() {
		this.chatMessageList.clear();
	}

	public void addChatMessage(String string) {
		while(this.mc.fontRenderer.getStringWidth(string) > 320) {
			int var2;
			for(var2 = 1; var2 < string.length() && this.mc.fontRenderer.getStringWidth(string.substring(0, var2 + 1)) <= 320; ++var2) {
			}

			this.addChatMessage(string.substring(0, var2));
			string = string.substring(var2);
		}

		this.chatMessageList.add(0, new ChatLine(string));

		while(this.chatMessageList.size() > 50) {
			this.chatMessageList.remove(this.chatMessageList.size() - 1);
		}

	}

	public void setRecordPlayingMessage(String string) {
		this.recordPlaying = "Now playing: " + string;
		this.recordPlayingUpFor = 60;
		this.field_22065_l = true;
	}

	public void addChatMessageTranslate(String string) {
		StringTranslate var2 = StringTranslate.getInstance();
		String var3 = var2.translateKey(string);
		this.addChatMessage(var3);
	}
}
