import { LandscapeSelectionElement } from '@/module/domain/landscape/LandscapeSelectionElement';
import { LandscapeSelectionTree } from '@/module/domain/landscape/LandscapeSelectionTree';
import { moduleSlug } from '../Modules.fixture';
import { describe, expect, it } from 'vitest';

describe('Landscape selection tree', () => {
  it('Should get empty optional for unknown element', () => {
    expect(new LandscapeSelectionTree([]).find(moduleSlug('unknown')).isEmpty()).toBe(true);
  });

  it('Should get selection element for known element', () => {
    const selection: LandscapeSelectionElement = {
      slug: moduleSlug('init'),
      selectable: true,
    };

    expect(new LandscapeSelectionTree([selection]).find(moduleSlug('init')).orElseThrow()).toEqual(selection);
  });
});
