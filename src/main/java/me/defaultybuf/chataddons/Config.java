package me.defaultybuf.chataddons;

import java.util.List;

import me.defaultybuf.chataddons.utils.ConfigAccessor;

public class Config {
  public static final String PLACEHOLDERS_INJECTOR = "bracket-placeholders-injector";
  public static final String CHAT_HOVER = "chat-hover";
  public static final String MESSAGES = "messages";
  public static final String CLEAR_CHAT = "clear-chat";
  public static final String LOCK_CHAT = "lock-chat";
  public static final String STAFF_CHAT = "staff-chat";
  public static final String MENTION = "mention";
  public static final String BROADCAST = "broadcast";

  private ConfigAccessor m_ConfigAccessor = null;

  public Config(Main plugin) {
    m_ConfigAccessor = new ConfigAccessor(plugin, "config.yml");
    reload();
  }

  public void reload() {
    m_ConfigAccessor.reloadConfig();
  }

  public boolean dependsOnPAPI() {
    final boolean ENABLE_CHAT_INJECTOR = getBoolean(PLACEHOLDERS_INJECTOR, "enabled");
    final boolean ENABLE_CHAT_HOVER = getBoolean(PLACEHOLDERS_INJECTOR, "enabled");

    return ENABLE_CHAT_INJECTOR || ENABLE_CHAT_HOVER;
  }

  public String getString(final String sectionTag, final String path) {
    return m_ConfigAccessor.getConfig().getString(sectionTag + "." + path);
  }

  public boolean getBoolean(final String sectionTag, final String path) {
    return m_ConfigAccessor.getConfig().getBoolean(sectionTag + "." + path);
  }

  public List<String> getStringList(final String sectionTag, final String path) {
    return m_ConfigAccessor.getConfig().getStringList(sectionTag + "." + path);
  }

  public int getInt(final String sectionTag, final String path) {
    return m_ConfigAccessor.getConfig().getInt(sectionTag + "." + path);
  }

  public String getPrefix() {
    return getString(MESSAGES, "prefix");
  }
}
