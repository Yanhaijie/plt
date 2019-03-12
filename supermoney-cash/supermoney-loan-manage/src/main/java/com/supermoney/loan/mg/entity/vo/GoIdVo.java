package com.supermoney.loan.mg.entity.vo;


import java.io.Serializable;

/**
 *  印尼公开身份库查询返回数据
 *  https://pilkada2017.kpu.go.id/pemilih/dpt/1/cari?nik=3172011002910005&nama=&namaPropinsi=DKI+JAKARTA&namaKabKota=&namaKecamatan=&namaKelurahan=
 */
public class GoIdVo  implements Serializable {

    private   String nik;

    private  String nama;

    private  String jenisKelamin;

    private String namaPropinsi;

    private String namaKabKota;

    private  String namaKecamatan;

    private  String namaKelurahan;

    private  String tps;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNamaPropinsi() {
        return namaPropinsi;
    }

    public void setNamaPropinsi(String namaPropinsi) {
        this.namaPropinsi = namaPropinsi;
    }

    public String getNamaKabKota() {
        return namaKabKota;
    }

    public void setNamaKabKota(String namaKabKota) {
        this.namaKabKota = namaKabKota;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    public String getNamaKelurahan() {
        return namaKelurahan;
    }

    public void setNamaKelurahan(String namaKelurahan) {
        this.namaKelurahan = namaKelurahan;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }
}
