package sst.bank.report.html;

import sst.common.html.HTMLHyperlinks;
import sst.common.html.HTMLListItem;
import sst.common.html.HTMLUnorderedList;

import java.io.File;

public class Navigation extends HTMLUnorderedList {
    public enum Pages {
        INDEX, BENEFICIARY,
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }

    public Navigation(Pages pages) {
        addListItem(menuItem("Main", address("index.html", pages, true), pages.equals(Pages.INDEX)));
        addListItem(menuItem("Janvier", address("summary-1.html", pages, false), pages.equals(Pages.JANUARY)));
        addListItem(menuItem("Février", address("summary-2.html", pages, false), pages.equals(Pages.FEBRUARY)));
        addListItem(menuItem("Mars", address("summary-3.html", pages, false), pages.equals(Pages.MARCH)));
        addListItem(menuItem("Avril", address("summary-4.html", pages, false), pages.equals(Pages.APRIL)));
        addListItem(menuItem("Mai", address("summary-5.html", pages, false), pages.equals(Pages.MAY)));
        addListItem(menuItem("Juin", address("summary-6.html", pages, false), pages.equals(Pages.JUNE)));
        addListItem(menuItem("Juillet", address("summary-7.html", pages, false), pages.equals(Pages.JULY)));
        addListItem(menuItem("Août", address("summary-8.html", pages, false), pages.equals(Pages.AUGUST)));
        addListItem(menuItem("Septembre", address("summary-9.html", pages, false), pages.equals(Pages.SEPTEMBER)));
        addListItem(menuItem("Octobre", address("summary-10.html", pages, false), pages.equals(Pages.OCTOBER)));
        addListItem(menuItem("Novembre", address("summary-11.html", pages, false), pages.equals(Pages.NOVEMBER)));
        addListItem(menuItem("Décembre", address("summary-12.html", pages, false), pages.equals(Pages.DECEMBER)));
    }

    private String address(String fileName, Pages pages, boolean index) {
        if (pages.equals(Pages.INDEX)) {
            if (index) {
                return fileName;
            } else {
                return "html" + File.separator + fileName;
            }
        } else {
            if (index) {
                return "../" + File.separator + fileName;
            } else {
                return fileName;
            }
        }

    }

    private HTMLListItem menuItem(String text, String address, boolean active) {
        HTMLListItem htmlListItem = new HTMLListItem();
        HTMLHyperlinks href = new HTMLHyperlinks().href(address);
        href.textContent(text);
        htmlListItem.addChild(href);
        if (active) {
            href.classId("active");
        }
        return htmlListItem;
    }
}
