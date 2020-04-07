package plugins.games;

import plugins.interfaces.IGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class FileReader implements IGame {


    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean unborrow() {
        return false;
    }

    @Override
    public boolean borrow() {
        return false;
    }

    @Override
    public boolean uninstall() {
        return false;
    }

    @Override
    public boolean install() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean download() {
        return false;
    }

    @Override
    public boolean buy() {
        return false;
    }

    @Override
    public boolean sell() {
        return false;
    }
}
