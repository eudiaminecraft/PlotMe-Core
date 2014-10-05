package com.worldcretornica.plotme_core.api;

public interface IPlayer extends IOfflinePlayer, ICommandSender, IEntity {

    public IWorld getWorld();

    public ILocation getLocation();

    public void teleport(ILocation location);
    
    public IItemStack getItemInHand();
}