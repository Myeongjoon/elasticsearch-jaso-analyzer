package org.elasticsearch.analysis;

/**
 * 테스트케이스 VO
 *
 * @author 최일규
 * @since 2016-02-13
 */
public class TestCaseVO {

    private String origin;
    private String compare;
    private String offset;

    public TestCaseVO(String origin, String compare) {
        this.origin = origin;
        this.compare = compare;
    }

    public TestCaseVO(String origin, String compare, String offset) {
        this.origin = origin;
        this.compare = compare;
        this.offset = offset;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCompare() {
        return compare;
    }

    public String getOffset() {
        return offset;
    }
}
