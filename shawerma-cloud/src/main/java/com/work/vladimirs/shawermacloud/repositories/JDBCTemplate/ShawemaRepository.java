package com.work.vladimirs.shawermacloud.repositories.JDBCTemplate;

import com.work.vladimirs.shawermacloud.entity.Shawerma;

public interface ShawemaRepository {

    Shawerma save(Shawerma newShawerma);
}
