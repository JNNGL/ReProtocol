package com.jnngl.reprotocol.data.recipe;

public class RecipeBookSettings {

  private final boolean open;
  private final boolean filterActive;

  public RecipeBookSettings(boolean open, boolean filterActive) {
    this.open = open;
    this.filterActive = filterActive;
  }

  public boolean isOpen() {
    return open;
  }

  public boolean isFilterActive() {
    return filterActive;
  }

  @Override
  public String toString() {
    return "RecipeBookSettings{" +
        "open=" + open +
        ", filterActive=" + filterActive +
        '}';
  }
}
