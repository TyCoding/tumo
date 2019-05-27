package cn.tycoding.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tycoding
 * @date 2019-03-25
 */
@Data
@Table(name = "tb_setting")
public class Setting implements Serializable {

    @Id
    private Long id;
    @Column(name = "site_name")
    private String siteName;
    @Column(name = "site_links")
    private Object siteLinks;
    @Column(name = "site_donation")
    private Object siteDonation;
    @Column(name = "site_music")
    private String siteMusic;
    private String about;
    @Column(name = "about_md")
    private String aboutMd;
}
