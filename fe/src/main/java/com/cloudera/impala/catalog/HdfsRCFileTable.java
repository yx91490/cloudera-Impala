// Copyright (c) 2011 Cloudera, Inc. All rights reserved.
package com.cloudera.impala.catalog;

import java.util.List;

import com.cloudera.impala.analysis.Expr;
import com.cloudera.impala.planner.DataSink;
import com.cloudera.impala.thrift.TTableDescriptor;
import com.cloudera.impala.thrift.TTableType;

/**
 * RCFileTable.
 *
 */
public class HdfsRCFileTable extends HdfsTable {

  private static final String rcfileInputFormat =
      "org.apache.hadoop.hive.ql.io.RCFileInputFormat";

  protected HdfsRCFileTable(TableId id, Db db, String name, String owner) {
    super(id, db, name, owner);
  }

  @Override
  public TTableDescriptor toThrift() {
    TTableDescriptor tTable = super.toThrift();
    tTable.setTableType(TTableType.HDFS_RCFILE_TABLE);
    return tTable;
  }

  public static boolean isRCFileTable(org.apache.hadoop.hive.metastore.api.Table msTbl) {
    return msTbl.getSd().getInputFormat().equals(rcfileInputFormat);
  }

  @Override
  public DataSink createDataSink(List<Expr> partitionKeyExprs, boolean overwrite) {
    throw new UnsupportedOperationException("HdfsRCFile Output Sink not implemented.");
  }
}
