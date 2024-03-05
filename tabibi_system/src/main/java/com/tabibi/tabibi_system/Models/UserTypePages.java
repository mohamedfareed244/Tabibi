package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "usertype_pages")
public class UserTypePages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upid;

    @ManyToOne
    @JoinColumn(name = "usertypeid", nullable = false)
    private UserTypes usertype;

    @ManyToOne
    @JoinColumn(name = "pageid", nullable = false)
    private Pages page;

    public UserTypePages() {
    }

    public UserTypePages(Long upid, UserTypes usertype, Pages page) {
        this.upid = upid;
        this.usertype = usertype;
        this.page = page;
    }

    public Long getUpid() {
        return this.upid;
    }

    public void setUpid(Long upid) {
        this.upid = upid;
    }

    public UserTypes getUsertype() {
        return this.usertype;
    }

    public void setUsertype(UserTypes usertype) {
        this.usertype = usertype;
    }

    public Pages getPage() {
        return this.page;
    }

    public void setPage(Pages page) {
        this.page = page;
    }

    public UserTypePages upid(Long upid) {
        setUpid(upid);
        return this;
    }

    public UserTypePages usertype(UserTypes usertype) {
        setUsertype(usertype);
        return this;
    }

    public UserTypePages page(Pages page) {
        setPage(page);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserTypePages)) {
            return false;
        }
        UserTypePages userTypePages = (UserTypePages) o;
        return Objects.equals(upid, userTypePages.upid) && Objects.equals(usertype, userTypePages.usertype) && Objects.equals(page, userTypePages.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upid, usertype, page);
    }

    @Override
    public String toString() {
        return "{" +
            " upid='" + getUpid() + "'" +
            ", usertype='" + getUsertype() + "'" +
            ", page='" + getPage() + "'" +
            "}";
    }

   
}

