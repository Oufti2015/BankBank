package sst.bank.report.html;

import sst.common.html.HTMLHyperlinks;
import sst.common.html.HTMLListItem;
import sst.common.html.HTMLUnorderedList;

public class Navigation extends HTMLUnorderedList {
    public enum Pages {
        INDEX,
        BENEFICIARY
    }

    public Navigation(Pages pages) {
        addListItem(menuItem("Main", "index.html", pages.equals(Pages.INDEX)));
        addListItem(menuItem("Beneficiaries", "beneficiary.html", pages.equals(Pages.BENEFICIARY)));
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
