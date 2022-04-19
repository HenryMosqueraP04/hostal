package com.ceiba.dia.adaptador.dao;

import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.puerto.dao.DaoDia;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoDiaMysql implements DaoDia {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace= "dia", value="listar")
    private static String sqlListar;

    public DaoDiaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoDia> listar() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoDia());
    }
}
