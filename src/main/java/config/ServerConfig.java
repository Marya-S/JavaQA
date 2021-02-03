package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface ServerConfig extends Config {
    @Key("url")
    String url();

    @Key("url_yandex")
    String url_yandex();

    @Key("url_tele2")
    String url_tele2();

    @Key("volt")
    String url_volt();
}
