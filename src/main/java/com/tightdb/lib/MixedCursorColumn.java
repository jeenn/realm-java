package com.tightdb.lib;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class MixedCursorColumn<Cursor, Query> extends AbstractColumn<Serializable, Cursor, Query> {

	public MixedCursorColumn(EntityTypes<?, ?, Cursor, Query> types, AbstractCursor<Cursor> cursor, int index, String name) {
		super(types, cursor, index, name);
	}

	@Override
	public Serializable get() {
		return TightDB.deserialize(cursor.rowset.getBinary(columnIndex, (int) cursor.getPosition()));
	}

	@Override
	public void set(Serializable value) {
		cursor.rowset.setBinary(columnIndex, (int) cursor.getPosition(), ByteBuffer.wrap(TightDB.serialize(value)));
	}

}
