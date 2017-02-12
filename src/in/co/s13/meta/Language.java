/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13.meta;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nika
 */
public interface Language {
    public Pattern generatePattern();
    public String getStyleClass(Matcher matcher);
    public ArrayList<String> getKeywords();
}
