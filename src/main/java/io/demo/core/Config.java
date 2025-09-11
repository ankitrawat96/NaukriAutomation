package io.demo.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    public App app;
    public Credentials credentials;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class App {
        public String baseUrl;
        public String loginPath;
        public boolean headless;
        public int implicitWaitSec;
        public int explicitWaitSec;
        public boolean windowMaximize;
        public String browser;
        public String filePath;
        public String DocFilePath;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Credentials {
        public String username;
        public String password;
    }
}
