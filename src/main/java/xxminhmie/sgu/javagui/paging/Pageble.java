package xxminhmie.sgu.javagui.paging;

import xxminhmie.sgu.javagui.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();

}
